package minesweeper.domain.model;

import minesweeper.domain.Enums.Action;
import minesweeper.domain.Enums.GameState;
import minesweeper.domain.exception.ExplosionException;
import minesweeper.view.View;

public class Game {
    private final Board board;
    private GameState gameState;

    public Game() {
        var difficulty = View.selectDifficulty();
        board = new Board(difficulty.getWidth(), difficulty.getHeight(), difficulty.getMineProbability());
        gameState = GameState.PLAYING;

        playGame();
    }

    private void playGame() {
        while (gameState == GameState.PLAYING) {
            View.displayBoard(board);

            if (checkWinCondition()) {
                gameState = GameState.WON;
                View.displayBoard(board);
                View.displayWinMessage();
                break;
            }

            var action = View.chooseAction();
            var position = View.choosePosition(board.getWidth(), board.getHeight());

            try {
                if (action == Action.REVEAL) {
                    board.revealCells(position.row(), position.column());
                } else if (action == Action.FLAG) {
                    board.toggleFlag(position.row(), position.column());
                }
            } catch (ExplosionException ex) {
                gameState = GameState.LOST;
                View.displayBoard(board);
                View.displayError(ex.getMessage());
                break;
            } catch (RuntimeException ex) {
                View.displayError(ex.getMessage());
            }
        }

        if (View.askPlayAgain()) {
            new Game();
        }
    }

    private boolean checkWinCondition() {
        for (int row = 0; row < board.getHeight(); row++) {
            for (int column = 0; column < board.getWidth(); column++) {
                var cell = board.getCellBoard().get(row).get(column);
                if (!cell.isMine() && !cell.isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }
}
