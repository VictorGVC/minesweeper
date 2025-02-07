package minesweeper.domain.exception;

public class ExplosionException extends RuntimeException {
    public ExplosionException(String error) {
        super("Game Over\n the mine exploded!!!");
    }
}
