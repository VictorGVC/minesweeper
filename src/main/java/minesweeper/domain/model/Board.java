package minesweeper.domain.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.SplittableRandom;

public class Board {

    private Map<Integer, HashMap<Integer, Cell>> cellBoard;
    private final int[][] offsets = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    private final int width;
    private final int height;

    public Board(int width, int height, double mineProbability) {

        if (height < 3 || width < 3) throw new IllegalArgumentException("Minimum board size is 3x3");

        this.width = width;
        this.height = height;

        generateBoard(width, height, mineProbability);
    }

    private void generateBoard(int width, int height, double mineProbability) {
        var random = new SplittableRandom();

        cellBoard = new HashMap<>();
        for (int row = 0; row < height; row++) {
            var rowMap = new HashMap<Integer, Cell>();
            cellBoard.put(row, rowMap);

            for (int column = 0; column < width; column++) {
                var isMine = random.nextInt(100) < mineProbability;

                var cell = new Cell(isMine);
                rowMap.put(column, cell);
            }
        }
    }

    public void revealCells(int row, int column) {
        if (row < 0 || row >= height || column < 0 || column >= width)
            throw new IllegalArgumentException("Invalid position");

        depthFirstSearch(row, column);
    }

    private void depthFirstSearch(int row, int column) {
        if (row < 0 || row >= height || column < 0 || column >= width) return;

        var currentCell = cellBoard.get(row).get(column);
        if (currentCell.isRevealed()) return;

        currentCell.reveal();

        var adjacentMines = getCellAdjacentMines(row, column);
        currentCell.setAdjacentMines(adjacentMines);
        if (adjacentMines != 0) return;

        Arrays.stream(offsets).forEach(offset -> {
            var nextRow = row + offset[0];
            var nextColumn = column + offset[1];
            depthFirstSearch(nextRow, nextColumn);
        });
    }

    private int getCellAdjacentMines(int row, int column) {
        int count = 0;

        for (int[] offset : offsets) {
            int nextRow = row + offset[0];
            int nextColumn = column + offset[1];

            if (cellBoard.containsKey(nextRow)
                    && cellBoard.get(nextRow).containsKey(nextColumn)
                    && cellBoard.get(nextRow).get(nextColumn).isMine()) {
                count++;
            }
        }

        return count;
    }

    public Map<Integer, HashMap<Integer, Cell>> getCellBoard() {
        return cellBoard;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void toggleFlag(int row, int column) {
        if (row < 0 || row >= height || column < 0 || column >= width)
            throw new IllegalArgumentException("Invalid position");

        var cell = cellBoard.get(row).get(column);
        cell.toggleFlag();
    }
}
