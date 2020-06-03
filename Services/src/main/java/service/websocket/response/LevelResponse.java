package service.websocket.response;

import Logic.Enums.FieldState;
import Logic.Models.Player;

import java.util.List;

public class LevelResponse {

    private int id = 0;
    private FieldState[][] maze = null;
    List<Player> players = null;
    private String fieldState = null;
    private String questionToUse = "";
    private int answer = 0;

    public String getQuestionToUse() {
        return questionToUse;
    }

    public void setQuestionToUse(String questionToUse) {
        this.questionToUse = questionToUse;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> player) {
        this.players = player;
    }

    public FieldState[][] getMaze() {
        return maze;
    }

    public void setMaze(FieldState[][] maze) {
        this.maze = maze;
    }

    public String getFieldState() {
        return fieldState;
    }

    public void setFieldState(String fieldState) {
        this.fieldState = fieldState;
    }
}
