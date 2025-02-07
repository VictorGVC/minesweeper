package minesweeper.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void Board_WhenInvalidHeight_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Board(0, 1));
    }

    @Test
    void Board_WhenInvalidWeight_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Board(1, 0));
    }

    @Test
    void Board_WhenCreated_HaveCorrectSize() {
        var board = new Board(2, 3);

        assertEquals(2, board.getCellBoard().get(0).size());
        assertEquals(3, board.getCellBoard().size());
    }
}
