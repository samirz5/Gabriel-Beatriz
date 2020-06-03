package Logic.GamePackage;

import Logic.Enums.FieldState;
import Logic.Enums.MazeDifficulty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


class MazeTest {

    MazeTest() {

    }

    @BeforeEach
    void setUp() {


    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testMaze_GenerateMaze_EqualsTrue_Size() {
        // Generate maze
        FieldState[][] maze = Maze.generateMaze(MazeDifficulty.EASY);

        // Count number rows and columns
        int expectedResult = 11;
        int actualResultHeight = maze.length;
        int actualResultWidth = maze[0].length;

        //Assert
        assertEquals(expectedResult, actualResultHeight);
        assertEquals(expectedResult, actualResultWidth);
    }

    @Test
    void testMaze_GenerateMaze_EqualsTrue_WallsOnSide() {
        // Generate maze
        FieldState[][] maze = Maze.generateMaze(MazeDifficulty.MEDIUM);

        // Check for walls
        FieldState expectedResult = FieldState.WALL;


        // Assert all sides to be walls
        for (int i = 0; i < maze.length; i++) {


            FieldState actualResult = maze[i][0];
            assertEquals(expectedResult, actualResult);
            actualResult = maze[i][maze.length - 1];
            assertEquals(expectedResult, actualResult);
        }

        for (int i = 0; i < maze.length; i++) {
            if (i == 1 || i == maze.length - 2) {
                break;
            }
            FieldState actualResult = maze[0][i];
            assertEquals(expectedResult, actualResult);
            actualResult = maze[maze.length-1][i];
            assertEquals(expectedResult, actualResult);
        }

    }

    @Test
    void testMaze_GenerateMaze_EqualsTrue_EntranceExit() {

        Random rnd = new Random();
        // run 10 tests with different sizes of mazes (maxSize 15, minSize 5).
        for (int i = 0; i < 10; i++) {
            int size = rnd.nextInt((15 - 5) + 1) + 5;

            // Generate maze
            FieldState[][] maze = Maze.generateMaze(MazeDifficulty.HARD);

            //Check open fields
            FieldState expectedResult = FieldState.OPEN;

            //Assert entrance and exit to be open
            /**
             * Entrance is always one right of the left bottom.
             * Exit is always one left of the top right.
             */
            FieldState actualResultExit = maze[0][maze.length-2];
            FieldState actualResultEntrance = maze[maze.length-1][1];
            assertEquals(expectedResult, actualResultEntrance);
            assertEquals(expectedResult, actualResultExit);
        }
    }


}