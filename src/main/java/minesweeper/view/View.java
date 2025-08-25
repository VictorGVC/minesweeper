package minesweeper.view;

import java.util.Scanner;
import minesweeper.domain.Enums.Action;
import minesweeper.domain.Enums.Difficulty;
import minesweeper.domain.model.Board;
import minesweeper.domain.model.Cell;

public abstract class View {

    public static Difficulty selectDifficulty() {
        var scanner = new Scanner(System.in);

        System.out.println(
                """
                        Select the difficulty:
                        1: Easy
                        2: Normal
                        3: Hard
                        """);

        var selectedDifficulty = scanner.nextInt();

        return switch (selectedDifficulty) {
            case 1 -> Difficulty.EASY;
            case 3 -> Difficulty.HARD;
            default -> Difficulty.NORMAL;
        };
    }

    public static void displayBoard(Board board) {
        clearConsole();

        System.out.print("   ");
        for (int col = 0; col < board.getWidth(); col++) {
            System.out.print(String.format("%-2d ", col));
        }
        System.out.println();

        System.out.print("  ");
        for (int col = 0; col < board.getWidth(); col++) {
            System.out.print("---");
        }
        System.out.println();

        for (int row = 0; row < board.getHeight(); row++) {
            System.out.print(row + " |");
            for (int column = 0; column < board.getWidth(); column++) {
                var cell = board.getCellBoard().get(row).get(column);
                System.out.print(getCellSymbol(cell) + " ");
            }
            System.out.println();
        }
    }

    private static String getCellSymbol(Cell cell) {
        if (cell.isFlagged()) return "F ";
        if (!cell.isRevealed()) return "■ ";
        if (cell.isMine()) return "* ";

        int adjacentMines = cell.getAdjacentMines();
        return adjacentMines == 0 ? "  " : adjacentMines + " ";
    }

    public static void displayError(String error) {
        System.out.println(FontColor.RED_FONT + "\n\n" + error + FontColor.WHITE_FONT);
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static Position choosePosition() {
        var scanner = new Scanner(System.in);

        System.out.println("""
                Select the row to reveal:
                """);

        var row = scanner.nextInt();

        System.out.println("""
                Select the column to reveal:
                """);

        var column = scanner.nextInt();
        scanner.close();
        return new Position(row, column);
    }

    public static Position choosePosition(int maxWidth, int maxHeight) {
        var scanner = new Scanner(System.in);

        System.out.println("Select the row (0-" + (maxHeight - 1) + "):");
        var row = scanner.nextInt();

        while (row < 0 || row >= maxHeight) {
            System.out.println("Invalid row! Please enter a value between 0 and " + (maxHeight - 1) + ":");
            row = scanner.nextInt();
        }

        System.out.println("Select the column (0-" + (maxWidth - 1) + "):");
        var column = scanner.nextInt();

        while (column < 0 || column >= maxWidth) {
            System.out.println("Invalid column! Please enter a value between 0 and " + (maxWidth - 1) + ":");
            column = scanner.nextInt();
        }
        scanner.close();
        return new Position(row, column);
    }

    public static Action chooseAction() {
        var scanner = new Scanner(System.in);

        System.out.println(
                """
                        Choose an action:
                        1: Reveal cell
                        2: Flag/Unflag cell
                        """);

        var actionValue = scanner.nextInt();

        while (actionValue != 1 && actionValue != 2) {
            System.out.println("Invalid action! Please enter 1 or 2:");
            actionValue = scanner.nextInt();
        }
        scanner.close();
        return actionValue == 1 ? Action.REVEAL : Action.FLAG;
    }

    public static void displayWinMessage() {
        System.out.println(FontColor.GREEN_FONT
                + """

                        🎉 CONGRATULATIONS! 🎉
                        You won the game!
                        """
                + FontColor.WHITE_FONT);
    }

    public static boolean askPlayAgain() {
        var scanner = new Scanner(System.in);

        System.out.println(
                """
                        Do you want to play again?
                        1: Yes
                        2: No
                        """);

        var choice = scanner.nextInt();
        scanner.close();
        return choice == 1;
    }
}
