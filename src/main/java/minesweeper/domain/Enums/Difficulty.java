package minesweeper.domain.Enums;

public enum Difficulty {
    EASY(12, 20, 10),
    NORMAL(15, 30, 15),
    HARD(17, 40, 20);

    private final double mineProbability;
    private final int width;
    private final int height;

    Difficulty(double mineProbability, int width, int height) {
        this.mineProbability = mineProbability;
        this.width = width;
        this.height = height;
    }

    public double getMineProbability() {
        return mineProbability;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
