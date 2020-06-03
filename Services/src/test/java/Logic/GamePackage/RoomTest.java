package Logic.GamePackage;

import Logic.Enums.Direction;
import Logic.Enums.FieldState;
import Logic.Enums.MazeDifficulty;
import Logic.Enums.QuestionDifficulty;
import Logic.Models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class RoomTest {

    private Room singlePlayerRoom;
    private Room multiplayerRoom;
    private Player singlePlayer;
    private Player multiPlayer1;
    private Player multiPlayer2;
    private Player multiPlayer3;
    private String operators;
    private QuestionDifficulty difficultyQuestion;
    private MazeDifficulty difficultyMaze;

    private FieldState[][] currentMaze;

    @BeforeEach
    void setUp() {
        operators = "+/-";
        difficultyQuestion = QuestionDifficulty.EASY;
        difficultyMaze = MazeDifficulty.MEDIUM;

        singlePlayerRoom = new Room(true, operators, difficultyQuestion, difficultyMaze);
        multiplayerRoom = new Room(false, operators, difficultyQuestion, difficultyMaze);

        multiplayerRoom.addPlayer(2, "Henk", 0, false, "avatar");
        multiplayerRoom.addPlayer(3, "Saartje", 0, false, "avatar");
        multiplayerRoom.addPlayer(4, "Lotte", 0, false, "avatar");

        multiPlayer1 = multiplayerRoom.getCurrentGame().getPlayerById(2);
        multiPlayer2 = multiplayerRoom.getCurrentGame().getPlayerById(3);
        multiPlayer3 = multiplayerRoom.getCurrentGame().getPlayerById(4);

        multiplayerRoom.createNewGame();

        currentMaze = Maze.generateMaze(MazeDifficulty.MEDIUM);
    }

    @Test
    void addPlayer() {
        Player expectedSinglePlayer = new Player(1, "Piet", 0, false, "avatar");

        singlePlayerRoom.addPlayer(1, "Piet", 0, false, "avatar");
        singlePlayer = singlePlayerRoom.getCurrentGame().getPlayerById(1);

        assertEquals(expectedSinglePlayer.getPlayerId(), singlePlayer.getPlayerId());
        assertEquals(expectedSinglePlayer.getUserName(), singlePlayer.getUserName());
        assertEquals(expectedSinglePlayer.getScore(), singlePlayer.getScore());
        assertEquals(expectedSinglePlayer.getTeacher(), singlePlayer.getTeacher());
    }

    @Test
    void updatePositionToOpenSpace() {
        int[] position = getPositionWithValue(FieldState.OPEN);
        int timer = 0;
        Direction direction = Direction.UP;
        int[] expected = new int[2];
        expected[0] = position[0];
        expected[1] = position[1];

        multiplayerRoom.updatePosition(2, position[0], position[1], direction, timer);

        assertArrayEquals(expected, multiPlayer1.getPosition());
    }

    @Test
    void updatePositionToObstacle() {
        int[] position = getPositionWithValue(FieldState.OBSTACLE);
        int timer = 0;
        Direction direction = null;
        int[] expected = new int[2];
        expected[0] = position[0];
        expected[1] = position[1];

        multiplayerRoom.updatePosition(2, position[0], position[1], direction, timer);

        assertArrayEquals(expected, multiPlayer1.getPosition());
        assertEquals(true, multiPlayer1.isFrozen());
    }

    @Test
    void updatePositionToFinish() {
        int x = 0;
        int y = currentMaze.length - 2;
        int timer = 300;
        int[] expected = new int[2];
        Direction direction = null;
        expected[0] = x;
        expected[1] = y;

        multiplayerRoom.updatePosition(2, x, y, direction, timer);
        int score = multiPlayer1.getScore();
        assertEquals(8, score);
        assertArrayEquals(expected, multiPlayer1.getPosition());
        assertEquals(true, multiPlayer1.isFinished());
    }

    @Test
    void updatePositionToAllPlayersFinished() {
        int x = 0;
        int y = currentMaze.length - 2;
        multiPlayer2.setScore(10);
        int[] expected = new int[2];
        expected[0] = currentMaze.length - 1;
        expected[1] = 1;
        Direction direction = null;
        int timer = 323;
        int timer1 = 230;
        int timer2 = 245;
        Game oldGame = multiplayerRoom.getCurrentGame();

        multiplayerRoom.updatePosition(2, x, y, direction, timer);
        multiplayerRoom.updatePosition(3, x, y, direction, timer1);
        multiplayerRoom.updatePosition(4, x, y, direction, timer2);
        int scorePlayer1 = multiPlayer1.getScore();
        int scorePlayer2 = multiPlayer2.getScore();
        int scorePlayer3 = multiPlayer3.getScore();

        assertEquals(scorePlayer1, 7);
        assertEquals(scorePlayer2, 20);
        assertEquals(scorePlayer3, 9);

        assertArrayEquals(expected, multiPlayer1.getPosition());
        assertEquals(true, multiPlayer1.isFinished());
        assertArrayEquals(expected, multiPlayer2.getPosition());
        assertEquals(true, multiPlayer2.isFinished());
        assertArrayEquals(expected, multiPlayer3.getPosition());
        assertEquals(true, multiPlayer3.isFinished());

        assertNotEquals(oldGame, multiplayerRoom.getCurrentGame());
    }

    @Test
    void updatePositionToWall() {
        int[] position = getPositionWithValue(FieldState.WALL);
        int[] expected = multiPlayer1.getPosition();
        int timer = 0;
        Direction direction = null;
        multiplayerRoom.updatePosition(2, position[0], position[1], direction, timer);

        assertArrayEquals(expected, multiPlayer1.getPosition());
    }

    @Test
    void notifyWhenReadyOnePlayerReady() {
        Game oldGame = multiplayerRoom.getCurrentGame();

        multiplayerRoom.notifyWhenReady(2);

        assertEquals(oldGame, multiplayerRoom.getCurrentGame());
    }

    @Test
    void notifyWhenReadyAllPlayersReady() {
        Game oldGame = multiplayerRoom.getCurrentGame();

        multiplayerRoom.notifyWhenReady(2);
        multiplayerRoom.notifyWhenReady(3);
        multiplayerRoom.notifyWhenReady(4);

        assertNotEquals(oldGame, multiplayerRoom.getCurrentGame());
    }

    @Test
    void updateScore() {
        int expected = 5;

        multiplayerRoom.updateScore(3, 5);

        assertEquals(expected, multiplayerRoom.getCurrentGame().getPlayerById(3).getScore());
    }

    @Test
    void isGameFullFalse() {
        assertFalse(multiplayerRoom.isGameFull());
    }

    @Test
    void isGameFullTrue() {
        multiplayerRoom.addPlayer(5, "Jan", 0, false, "avatar");

        assertTrue(multiplayerRoom.isGameFull());
    }

    private int[] getPositionWithValue(FieldState f) {
        int[] result = new int[2];
        int x = 0;
        int y = 0;

        while (!(currentMaze[x][y] == f)) {
            x = (int) (14.0 * Math.random());
            y = (int) (14.0 * Math.random());
            multiPlayer1.setPosition(x, y - 1);
            result[0] = x;
            result[1] = y;
        }
        return result;
    }
}