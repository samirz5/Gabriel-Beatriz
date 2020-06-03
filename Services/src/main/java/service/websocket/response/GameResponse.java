package service.websocket.response;

import Logic.Enums.FieldState;
import Logic.Models.Player;

import java.util.List;

public class GameResponse {

    List<Player> players = null;
    Boolean begin = false;
    private FieldState[][] maze = null;

    public FieldState[][] getMaze() {
        return maze;
    }

    public void setMaze(FieldState[][] maze) {
        this.maze = maze;
    }

    public Boolean getBegin() {
        return begin;
    }

    public void setBegin(Boolean begin) {
        this.begin = begin;
    }

    public List<Player> getPlayers() {
        return players;
    }


    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}
