package Logic.Models;

public abstract class User {
    private int playerId;
    private String userName;
    private boolean teacher;
    private String avatar;

    public User(int playerId, String userName) {
        this.playerId = playerId;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public boolean getTeacher() {
        return teacher;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    void setTeacher(boolean teacher) {
        this.teacher = teacher;
    }
}
