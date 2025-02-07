package minesweeper.domain.model;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private HashMap<Integer, HashMap<Integer, Cell>> cellBoard;

    public Board(int height, int width) {}

    public Map<Integer, HashMap<Integer, Cell>> getCellBoard() {
        return cellBoard;
    }
}
