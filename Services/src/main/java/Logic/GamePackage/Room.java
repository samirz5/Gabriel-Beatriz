package Logic.GamePackage;

import Logic.Enums.Direction;
import Logic.Enums.FieldState;
import Logic.Enums.MazeDifficulty;
import Logic.Enums.QuestionDifficulty;
import Logic.Interfaces.IGame;
import Logic.Models.Player;


import java.util.ArrayList;
import java.util.List;

public class Room implements IGame {

    private String operators;
    private QuestionDifficulty difficultyQuestion;
    private MazeDifficulty difficultyMaze;
    private boolean singlePlayer;
    private Game currentGame;


    /**
     * getter gemaakt om spelers op te halen in een game.
     *
     * @return
     */
    public List<Player> getPlayers() {
        return currentGame.getPlayers();
    }

    public Room(boolean singlePlayer, String operators, QuestionDifficulty difficultyQuestion, MazeDifficulty difficultyMaze) {
        this.singlePlayer = singlePlayer;
        this.operators = operators;
        this.difficultyQuestion = difficultyQuestion;
        this.difficultyMaze = difficultyMaze;
        createNewGame();
    }

    /**
     * Get player by id from database?
     */
    public void addPlayer(int id, String userName, int score, boolean teacher, String avatarUrl) {
        Player player = new Player(id, userName, score, teacher, avatarUrl);
        player.setPosition(1, difficultyMaze.getSize() - 2);
        currentGame.addPlayer(player);
    }


    public FieldState updatePosition(int id, int x, int y, Direction direction, int timer) {
        FieldState fieldState = currentGame.updatePosition(id, x, y, direction, timer);
        if (currentGame.allFinished) {
            createNewGame();
        }
        return fieldState;
    }

    /**
     * x en y omgedraait om beginposities goed te krijgen.
     */
    public void createNewGame() {
        currentGame = new Game(new ArrayList<>(), operators, difficultyQuestion, difficultyMaze, this);

        /*
        for (Player player : currentGame.getPlayers()) {
            player.setPosition(1, difficultyMaze.getSize() - 1);
            updateLives(player.getId(), 3);
        }
         */
    }


    //todo: if statement moet anders. zodra 1 speler erin zit en op gereed klikt begint al het spel.
    public boolean notifyWhenReady(int id) {
        int playersReady = 0;
        currentGame.getPlayerById(id).setReady(true);

        //Stream<Player> playersReady = players.stream().filter(p -> p.isReady());
        for (Player player :
                currentGame.getPlayers()) {
            if (player.isReady()) {
                playersReady++;
            }
        }
        if (playersReady == currentGame.getPlayers().size()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Op welke manier krijgen spelers scores? Goed beantwoorde vragen? Tijd die afgelegd wordt tot finish?
     */
    public void updateScore(int id, int score) {
        currentGame.updateScore(id, score);
    }

    public boolean isGameFull() {
        return currentGame.getPlayers().size() == 4;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public String getOperators() {
        return operators;
    }

    public QuestionDifficulty getDifficultyQuestion() {
        return difficultyQuestion;
    }


}
