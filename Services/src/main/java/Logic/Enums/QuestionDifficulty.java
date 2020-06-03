package Logic.Enums;

public enum QuestionDifficulty {
    EASY(11),
    MEDIUM(101),
    HARD(1001);

    private final int i;

    QuestionDifficulty(int i) {
        this.i = i;
    }

    public int getI() {
        return this.i;
    }
}
