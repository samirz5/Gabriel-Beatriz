package Logic.GamePackage;

import Logic.Enums.Direction;
import Logic.Enums.FieldState;
import Logic.Enums.MazeDifficulty;

import java.util.*;

public class Maze {

    private static int numberOfObstacles;
    private static int size;
    private static int percentage;

    public static FieldState[][] generateMaze(MazeDifficulty difficulty) {

        size = difficulty.getSize();
        percentage = difficulty.getPercentageObstacles();
        numberOfObstacles = size * size * percentage / 100;

        if (size % 2 == 0) // Als height of width een even getal zijn, maak ze oneven. Om bugs te verkomen.
            size++;

        int width = size;
        int height = size;

        FieldState[][] maze = new FieldState[width][height];

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                maze[i][j] = FieldState.WALL;
        //zet alles naar muur

        Random random = new Random();

        int randomHeight = random.nextInt(height);
        while (randomHeight % 2 == 0) {
            randomHeight = random.nextInt(height);
        }

        int randomWidth = random.nextInt(width);
        while (randomWidth % 2 == 0) {
            randomWidth = random.nextInt(width);
        }
        // Startpunt
        maze[randomWidth][randomHeight] = FieldState.OPEN;

        makePath(maze, randomWidth, randomHeight, height, width, percentage);

        maze[height - 1][1] = FieldState.OPEN;
        maze[0][width - 2] = FieldState.OPEN;

        return maze;
    }

    private static void makePath(FieldState[][] maze, int column, int row, int height, int width, int percentage) {
        Random random = new Random();
        Direction[] randomDirections = generateRandomDirections();
        for (Direction randomDirection : randomDirections) {

            switch (randomDirection) {
                case UP:
                    //kijk of index buiten doolhof valt
                    if (column - 2 <= 0)
                        continue;
                    if (maze[column - 2][row] != FieldState.OPEN) {
                        maze[column - 2][row] = FieldState.OPEN;
                        if (random.nextInt(10) > 3 && numberOfObstacles >= 0) {
                            maze[column - 1][row] = FieldState.OBSTACLE;
                            numberOfObstacles--;
                        } else {
                            maze[column - 1][row] = FieldState.OPEN;
                        }
                        makePath(maze, column - 2, row, height, width, percentage);
                    }
                    break;
                case RIGHT:
                    if (row + 2 >= width - 1)
                        continue;
                    if (maze[column][row + 2] != FieldState.OPEN) {
                        maze[column][row + 2] = FieldState.OPEN;
                        if (random.nextInt(10) > 3 && numberOfObstacles >= 0) {
                            maze[column][row + 1] = FieldState.OBSTACLE;
                            numberOfObstacles--;
                        } else {
                            maze[column][row + 1] = FieldState.OPEN;
                        }
                        makePath(maze, column, row + 2, height, width, percentage);
                    }
                    break;
                case DOWN:
                    if (column + 2 >= height - 1)
                        continue;
                    if (maze[column + 2][row] != FieldState.OPEN) {
                        maze[column + 2][row] = FieldState.OPEN;
                        if (random.nextInt(10) > 3 && numberOfObstacles >= 0) {
                            maze[column + 1][row] = FieldState.OBSTACLE;
                            numberOfObstacles--;
                        } else {
                            maze[column + 1][row] = FieldState.OPEN;
                        }
                        makePath(maze, column + 2, row, height, width, percentage);
                    }
                    break;
                case LEFT:
                    if (row - 2 <= 0)
                        continue;
                    if (maze[column][row - 2] != FieldState.OPEN) {
                        maze[column][row - 2] = FieldState.OPEN;
                        if (random.nextInt(10) > 3 && numberOfObstacles >= 0) {
                            maze[column][row - 1] = FieldState.OBSTACLE;
                            numberOfObstacles--;
                        } else {
                            maze[column][row - 1] = FieldState.OPEN;
                        }
                        makePath(maze, column, row - 2, height, width, percentage);
                    }
                    break;
            }
        }
    }

    private static Direction[] generateRandomDirections() {
        Direction[] directions = Direction.values();
        Collections.shuffle(Arrays.asList(directions));

        return directions;
    }
}
