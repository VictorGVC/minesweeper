package minesweeper.domain.model;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.*;

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
}