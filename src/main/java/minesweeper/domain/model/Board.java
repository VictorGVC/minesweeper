package minesweeper.domain.model;

import java.util.HashMap;
import java.util.Map;
import java.util.SplittableRandom;

public class Board {

    private final Map<Integer, HashMap<Integer, Cell>> cellBoard;

    public Board(int height, int width) {
        this(height, width, 12);
    }

    public Board(int height, int width, double mineProbability) {

        if (height < 3 || width < 3)
            throw new IllegalArgumentException("Minimum board size is 3x3");

        SplittableRandom random = new SplittableRandom();

        cellBoard = new HashMap<>();
        for (int row = 0; row < height; row++) {
            var rowMap = new HashMap<Integer, Cell>();
            cellBoard.put(row, rowMap);

            for (int column = 0; column < width; column++) {
                var isMine = random.nextInt(99) < mineProbability;

                var cell = new Cell(isMine);
                rowMap.put(column, cell);
            }
        }
    }

    public Map<Integer, HashMap<Integer, Cell>> getCellBoard() {
        return cellBoard;
    }
}
