package minesweeper.domain.model;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.*;

import minesweeper.domain.exception.ExplosionException;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CellTest {

    @Test
    void toggleFlag_WhenFlaggedAndNotRevealed_CellIsNotFlagged() {

        var cell = Instancio.of(Cell.class)
                .set(field(Cell::isFlagged), true)
                .set(field(Cell::isRevealed), false)
                .create();

        cell.toggleFlag();

        assertFalse(cell.isFlagged());
    }

    @Test
    void toggleFlag_WhenNotFlaggedNotRevealed_CellIsFlagged() {
        var cell = Instancio.of(Cell.class)
                .set(field(Cell::isFlagged), false)
                .set(field(Cell::isRevealed), false)
                .create();

        cell.toggleFlag();

        assertTrue(cell.isFlagged());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void toggleFlag_WhenRevealed_FlagKeepValue(boolean flag) {
        var cell = Instancio.of(Cell.class)
                .set(field(Cell::isFlagged), flag)
                .set(field(Cell::isRevealed), true)
                .create();

        cell.toggleFlag();

        assertEquals(flag, cell.isFlagged());
    }

    @Test
    void reveal_WhenNotFlagged_ReviewCell() {
        var cell = Instancio.of(Cell.class)
                .set(field(Cell::isFlagged), false)
                .set(field(Cell::isRevealed), false)
                .set(field(Cell::isMine), false)
                .create();

        cell.reveal();

        assertTrue(cell.isRevealed());
    }

    @Test
    void reveal_WhenFlagged_NotReviewCell() {
        var cell = Instancio.of(Cell.class)
                .set(field(Cell::isFlagged), true)
                .set(field(Cell::isRevealed), false)
                .set(field(Cell::isMine), false)
                .create();

        cell.reveal();

        assertFalse(cell.isRevealed());
    }

    @Test
    void reveal_WhenNotFlaggedAndIsMine_ThrowExplosionException() {
        var cell = Instancio.of(Cell.class)
                .set(field(Cell::isFlagged), false)
                .set(field(Cell::isRevealed), false)
                .set(field(Cell::isMine), true)
                .create();

        assertThrows(ExplosionException.class, cell::reveal);
    }
}
