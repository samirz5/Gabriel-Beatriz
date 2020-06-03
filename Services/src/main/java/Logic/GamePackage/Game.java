package Logic.GamePackage;

import Logic.Enums.Direction;
import Logic.Enums.FieldState;
import Logic.Enums.MazeDifficulty;
import Logic.Enums.QuestionDifficulty;
import Logic.Models.Player;

import java.util.List;
import java.util.stream.Stream;

public class Game {


    public FieldState[][] getStartingMaze() {
        return startingMaze;
    }

    private FieldState[][] startingMaze;
    //threads voor timer
    private int timer;
    private List<Player> players;
    public boolean allFinished = false;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    private String operators;
    private QuestionDifficulty difficultyQuestion;
    private MazeDifficulty difficultyMaze;


    public Game(List<Player> players, String operators, QuestionDifficulty difficultyQuestion, MazeDifficulty difficultyMaze, Room room) {
        this.players = players;
        this.operators = operators;
        this.difficultyQuestion = difficultyQuestion;
        this.difficultyMaze = difficultyMaze;

        this.startingMaze = Maze.generateMaze(difficultyMaze);
    }

    public void addPlayer(Player player) {
        for (Player playerInGame :
                players) {
            if (playerInGame.getPlayerId() == player.getPlayerId()) {
                return;
            }
        }
        player.setMaze(cloneMaze(startingMaze));
        player.setPosition(1, player.getMaze().length - 1);
        players.add(player);
    }

    private FieldState[][] cloneMaze(FieldState[][] maze) {
        FieldState[][] FS1 = maze.clone();
        for (int i = 0; i < FS1.length; i++) {
            FS1[i] = FS1[i].clone();
        }
        return FS1;
    }


    public Player getPlayerById(int id) {
        for (Player player : players) {
            if (player.getPlayerId() == id) {
                return player;
            }
        }
        return null;
    }

    public int[] positionToBeChecked(int id, int[] xy, Direction direction) {
//        int xx =   //todo hier wordt positie opgehaald van de spelers vanuit de backend.
//        int yy = getPlayerById(id).getPosition()[1];

        switch (direction) {
            case UP:
                xy[1] = xy[1] - 1;
                return xy;
            case DOWN:
                xy[1] = xy[1] + 1;
                return xy;
            case LEFT:
                xy[0] = xy[0] - 1;
                return xy;
            case RIGHT:
                xy[0] = xy[0] + 1;
                return xy;
            default:
                return xy;
        }
    }

    FieldState updatePosition(int playerId, int x, int y, Direction direction, int timer) {
        Player player = getPlayerById(playerId);
        assert player != null;
        int[] xy = positionToBeChecked(playerId, player.getPosition(), direction);

        if (player.getMaze()[xy[1]][xy[0]] == FieldState.WALL) {
            return FieldState.WALL;
        } else if (player.getMaze()[xy[1]][xy[0]] == FieldState.OBSTACLE) {
            player.setPosition(xy[0], xy[1]);
            return FieldState.OBSTACLE;

        } else if (player.getMaze()[xy[1]][xy[0]] == FieldState.OPEN) {
            player.setPosition(xy[0], xy[1]);
            if (xy[1] == 0 && xy[0] == difficultyMaze.getSize() - 2) {
                player.setFinished(true);
                updateScore(playerId, player.getScore() + calculateScore(timer));
                checkIfAllFinished();
                if (allFinished) {
                    for (Player player1 :
                            players) {
                        player1.setFinished(false);
                    }
                    allFinished = false;
                    return FieldState.ALLFINISHED;
                }
                return FieldState.FINISHED;
            }
            return FieldState.OPEN;
        }
        return null;
    }

    //tijd van timer wordt in ms(?) meegegeven
    private int calculateScore(int timer) {
        int score = 10;
//        if(timer <= 240){
//            score = 10;
//        }
        if (timer >= 240) {
            int secOver3min = timer - 240;
            for (int i = 0; i < secOver3min; i += 30) {
                score = score - 1;
            }
            if (score < 0) {
                score = 0;
            }
        }
        return score;
    }

    private void checkIfAllFinished() {
        Stream<Player> playersFinished = players.stream().filter(p -> p.isFinished());
        if (playersFinished.toArray().length == players.size()) {
            allFinished = true;
            newMaze();
        }
    }

    public void removeObstacle(int playerId) {
        Player player = getPlayerById(playerId);
        int[] pos = player.getPosition();
        player.getMaze()[pos[1]][pos[0]] = FieldState.OPEN;
    }

    public void updateScore(int id, int score) {
        getPlayerById(id).setScore(score);
    }

    public void newMaze() {
        this.startingMaze = Maze.generateMaze(difficultyMaze);
        for (Player player : players) {
            player.setPosition(1, difficultyMaze.getSize() - 1);
            player.setMaze(cloneMaze(startingMaze));
            //updateLives(player.getId(), 3);
        }
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
