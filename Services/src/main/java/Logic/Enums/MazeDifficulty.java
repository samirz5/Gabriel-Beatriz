package Logic.Enums;

public enum MazeDifficulty {
    EASY(10, 20),
    MEDIUM(15, 30),
    HARD(20, 40);

    private final int size;
    private final int percentageObstacles;

    MazeDifficulty(int size, int percentageObstacles) {
        this.size = size;
        this.percentageObstacles = percentageObstacles;
    }

    public int getSize() {
        return this.size;
    }

    public int getPercentageObstacles() {
        return this.percentageObstacles;
    }
}
