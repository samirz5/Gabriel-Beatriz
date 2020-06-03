package Logic.GamePackage;

import Logic.Enums.QuestionDifficulty;

import java.util.Random;

public class Obstacle {
    private int num1;
    private int num2;
    private char operator;
    private String question;

    public String generateQuestion(String operators, QuestionDifficulty difficulty) {
        Random random = new Random();

         num1 = random.nextInt(difficulty.getI());
         num2 = random.nextInt(difficulty.getI());

        int mathOpIndex = operators.length();
        Random r = new Random();
        operator = operators.charAt(r.nextInt(mathOpIndex));
        question = num1 + " " + operator + " " + num2;
        return question;
    }

    public int generateAnswer(){
        String[] components = question.split(" ");
        char operatorToUse = components[1].charAt(0);
        switch(operatorToUse){
            case '+':
                return Integer.parseInt(components[0]) + Integer.parseInt(components[2]);
            case '-':
                return Integer.parseInt(components[0]) - Integer.parseInt(components[2]);
            case '/':
                return Integer.parseInt(components[0]) / Integer.parseInt(components[2]);
            case '*':
                return Integer.parseInt(components[0]) * Integer.parseInt(components[2]);
            default:
                return -10000000;
        }
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


}
