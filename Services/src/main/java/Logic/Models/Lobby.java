package Logic.Models;
import Logic.Enums.MazeDifficulty;
import Logic.Enums.QuestionDifficulty;
import Logic.GamePackage.Room;
import Logic.Interfaces.IGame;

import java.util.ArrayList;
import java.util.List;

public class Lobby {

    private static final List<IGame> games = new ArrayList<>();

    public IGame createSinglePlayerGame(String operators, QuestionDifficulty difficulty, MazeDifficulty difficultyMaze) {
        IGame game = new Room(true, operators, difficulty, difficultyMaze);
        games.add(game);
        return game;
    }

    public IGame getNonFullMultiPlayerGame(String operators, QuestionDifficulty difficulty, MazeDifficulty difficultyMaze) {

        if (games.size() == 0 || games.get(games.size() - 1).isGameFull()) {

            IGame g = new Room(false, operators, difficulty, difficultyMaze);
            games.add(g);
            return g;

        } else {
            IGame lastGame = games.get(games.size() - 1);
            return lastGame;
        }
    }

    public void remove(Room room) {
        games.remove(room);
    }
}
