package Logic.Models;

import Logic.Enums.FieldState;

public class Player extends User {
    private int coins;
    private int lives;
    private int score;
    private int x;
    private int y;
    private boolean frozen;
    private boolean ready;
    private boolean finished;
    private String avatarUrl;
    private FieldState[][] maze;

    public Player(int id, String userName, int score, boolean teacher, String avatarUrl) {
        super(id, userName);
        this.score = score;
        this.setTeacher(teacher);
        this.setAvatar(avatarUrl);
        this.lives = 3;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isReady() {
        return ready;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public FieldState[][] getMaze() {
        return maze;
    }

    public void setMaze(FieldState[][] maze) {
        this.maze = maze;
    }

    public int[] getPosition() {
        int[] result = new int[2];
        result[0] = x;
        result[1] = y;
        return result;
    }
}
