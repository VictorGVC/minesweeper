package minesweeper.domain.exception;

public class ExplosionException extends RuntimeException {
    public ExplosionException() {
        super("Game Over\n the mine exploded!!!");
    }
}
