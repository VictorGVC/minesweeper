package minesweeper.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import minesweeper.domain.exception.ExplosionException;

class BoardTest {
    @Test
    void Board_WhenCreated_HaveCorrectSize() {
        var board = new Board(3, 4, 0);

        assertEquals(3, board.getCellBoard().get(0).size());
        assertEquals(4, board.getCellBoard().size());
    }

    @Test
    void Board_WhenCreatedWithMinimumSize_ShouldWork() {
        var board = new Board(3, 3, 0);

        assertEquals(3, board.getWidth());
        assertEquals(3, board.getHeight());
        assertEquals(3, board.getCellBoard().size());
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2 })
    void Board_WhenCreatedWithInvalidSize_ThrowsException(int size) {
        assertThrows(IllegalArgumentException.class, () -> new Board(size, 3, 0));
        assertThrows(IllegalArgumentException.class, () -> new Board(3, size, 0));
    }

    @Test
    void Board_WhenCreatedWithMines_ShouldHaveMines() {
        var board = new Board(10, 10, 100);

        int mineCount = 0;
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                if (board.getCellBoard().get(row).get(col).isMine()) {
                    mineCount++;
                }
            }
        }

        assertTrue(mineCount > 0);
    }

    @Test
    void Board_WhenCreatedWithNoMines_ShouldHaveNoMines() {
        var board = new Board(5, 5, 0);

        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                assertFalse(board.getCellBoard().get(row).get(col).isMine());
            }
        }
    }

    @Test
    void revealCells_WhenValidPosition_ShouldRevealCell() {
        var board = new Board(5, 5, 0);
        board.revealCells(2, 2);

        assertTrue(board.getCellBoard().get(2).get(2).isRevealed());
    }

    @Test
    void revealCells_WhenInvalidPosition_ThrowsException() {
        var board = new Board(5, 5, 0);
        assertThrows(IllegalArgumentException.class, () -> board.revealCells(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> board.revealCells(0, -1));
        assertThrows(IllegalArgumentException.class, () -> board.revealCells(5, 0));
        assertThrows(IllegalArgumentException.class, () -> board.revealCells(0, 5));
    }

    @Test
    void revealCells_WhenCellHasNoAdjacentMines_ShouldRevealAdjacentCells() {
        var board = new Board(5, 5, 0);
        board.revealCells(2, 2);

        int revealedCount = 0;
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                if (board.getCellBoard().get(row).get(col).isRevealed()) {
                    revealedCount++;
                }
            }
        }

        assertTrue(revealedCount > 1);
    }

    @Test
    void revealCells_WhenCellIsMine_ThrowsExplosionException() {
        var mineBoard = new Board(3, 3, 100);

        assertThrows(ExplosionException.class, () -> mineBoard.revealCells(1, 1));
    }

    @Test
    void toggleFlag_WhenValidPosition_ShouldToggleFlag() {
        var board = new Board(5, 5, 0);
        var cell = board.getCellBoard().get(1).get(1);
        boolean initialFlag = cell.isFlagged();

        board.toggleFlag(1, 1);

        assertEquals(!initialFlag, cell.isFlagged());
    }

    @Test
    void toggleFlag_WhenInvalidPosition_ThrowsException() {
        var board = new Board(5, 5, 0);
        assertThrows(IllegalArgumentException.class, () -> board.toggleFlag(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> board.toggleFlag(0, -1));
        assertThrows(IllegalArgumentException.class, () -> board.toggleFlag(5, 0));
        assertThrows(IllegalArgumentException.class, () -> board.toggleFlag(0, 5));
    }

    @Test
    void revealCells_WhenCellAlreadyRevealed_ShouldNotChangeState() {
        var board = new Board(5, 5, 0);
        board.revealCells(0, 0);
        var cell = board.getCellBoard().get(0).get(0);
        int initialAdjacentMines = cell.getAdjacentMines();

        // Reveal same cell again
        board.revealCells(0, 0);

        assertTrue(cell.isRevealed());
        assertEquals(initialAdjacentMines, cell.getAdjacentMines());
    }

    @Test
    void Board_WhenCreatedWithDifferentDimensions_ShouldHaveCorrectDimensions() {
        var rectBoard = new Board(8, 12, 0);

        assertEquals(8, rectBoard.getWidth());
        assertEquals(12, rectBoard.getHeight());
        assertEquals(12, rectBoard.getCellBoard().size());
        assertEquals(8, rectBoard.getCellBoard().get(0).size());
    }
}
