package minesweeper.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void Board_WhenInvalidHeight_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Board(2, 3));
    }

    @Test
    void Board_WhenInvalidWeight_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Board(3, 2));
    }

    @Test
    void Board_WhenCreated_HaveCorrectSize() {
        var board = new Board(3, 4);

        assertEquals(3, board.getCellBoard().get(0).size());
        assertEquals(4, board.getCellBoard().size());
    }
}
