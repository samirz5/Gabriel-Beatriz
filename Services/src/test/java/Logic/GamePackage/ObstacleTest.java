package Logic.GamePackage;

import Logic.Enums.QuestionDifficulty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObstacleTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    Obstacle obstacle = new Obstacle();

    @Test
    void generateQuestion_Test() {
        String operatorsGroep3 = "+-";
        QuestionDifficulty easy = QuestionDifficulty.EASY;

        String question = obstacle.generateQuestion(operatorsGroep3, easy);
        String[] parts = question.split(" ");

        assertTrue(Integer.parseInt(parts[0]) <= 10);
        assertFalse(parts[1].equals("/") || parts[1].equals("*"));
        assertTrue(parts[1].equals("-") || parts[1].equals("+"));
        assertTrue(Integer.parseInt(parts[2]) <= 10);

    }

    @Test
    void generateQuestion_2_Test() {
        String operatorsAll = "+-/*";
        QuestionDifficulty hard = QuestionDifficulty.HARD;

        String question = obstacle.generateQuestion(operatorsAll, hard);
        String[] parts = question.split(" ");

        assertTrue(Integer.parseInt(parts[0]) <= 1000);
        assertTrue(parts[1].equals("-") || parts[1].equals("+") || parts[1].equals("/") || parts[1].equals("*"));
        assertTrue(Integer.parseInt(parts[2]) <= 1000);

    }

    @Test
    void generateAnswer_Test(){
        int component = 2;
        char component2 = '+';
        int component3 = 1;
        obstacle.setQuestion((component + " " + component2 + " " + component3));
        obstacle.generateAnswer();
        assertEquals(3, obstacle.generateAnswer());

    }
}