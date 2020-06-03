package Logic.Interfaces;

import Logic.Enums.Direction;
import Logic.Enums.FieldState;

public interface IGame {

    FieldState updatePosition(int id, int x, int y, Direction direction, int timer);

    void updateScore(int id, int score);

    boolean notifyWhenReady(int id);

    boolean isGameFull();

    void createNewGame();
}
