package minesweeper.domain.model;

import minesweeper.domain.exception.ExplosionException;

public class Cell {
    private final boolean mine;
    private boolean flagged;
    private boolean revealed;
    private int adjacentMines;

    public Cell(boolean mine) {
        this.mine = mine;
    }

    public boolean isMine() {
        return mine;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

    public void toggleFlag() {
        if (!isRevealed()) flagged = !flagged;
    }

    public void reveal() {
        if (isFlagged()) return;

        if (isMine()) throw new ExplosionException();

        revealed = true;
    }
}
