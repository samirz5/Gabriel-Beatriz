package Entities;

public class DalUser {

    private int playerId;
    private String userName;
    private String passWord;
    private boolean teacher;
    private int coins;
    private int score;
    private String avatarUrl;
    private String firstName;
    private String lastName;

    public DalUser(int playerId, String userName, String passWord, String firstName, String lastName, boolean teacher, int coins, int score, String avatarUrl) {
        this.playerId = playerId;
        this.userName = userName;
        this.passWord = passWord;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teacher = teacher;
        this.coins = coins;
        this.score = score;
        this.avatarUrl = avatarUrl;
    }

    public DalUser(String userName, String passWord, boolean teacher, String avatarUrl, String firstName, String lastName) {
        this.userName = userName;
        this.passWord = passWord;
        this.teacher = teacher;
        this.avatarUrl = avatarUrl;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public boolean getTeacherId() {
        return teacher;
    }

    public int getCoins() {
        return coins;
    }

    public int getScore() {
        return score;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
