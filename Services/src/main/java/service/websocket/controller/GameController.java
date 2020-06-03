package service.websocket.controller;


import Logic.Enums.Direction;
import Logic.Enums.FieldState;
import Logic.Enums.MazeDifficulty;
import Logic.Enums.QuestionDifficulty;
import Logic.GamePackage.Game;
import Logic.GamePackage.Room;
import Logic.GamePackage.Obstacle;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import service.websocket.model.AddPlayerMessage;
import service.websocket.model.GameMessage;
import service.websocket.model.QuestionMessage;
import service.websocket.model.UpdatePositionMessage;
import service.websocket.response.GameResponse;
import service.websocket.response.LevelResponse;

import java.security.Principal;

@Controller
public class GameController {
    Room room = new Room(false, "+", QuestionDifficulty.EASY, MazeDifficulty.MEDIUM);


    @MessageMapping("/connect")
    @SendTo("/topic/public")
    public GameResponse addPlayer(AddPlayerMessage message) {
        room.addPlayer(message.getPlayerId(), message.getUserName(), message.getScore(),
                message.getTeacher(), message.getAvatarUrl());
        GameResponse response = new GameResponse();
        response.setPlayers(room.getPlayers());
        return response;
    }

    @MessageMapping("/game/connect")
    @SendTo("/topic/level")
    public GameResponse connect(GameMessage message){
        GameResponse response = new GameResponse();
        response.setPlayers(room.getPlayers());
        return response;
    }

    /**
     * @param message
     * @return, zodra de vierde speler gereed is start het spel.
     */
    @MessageMapping("/setready")
    @SendTo({"/topic/public"})
    private GameResponse setPlayerReady(GameMessage message) {
        System.out.println(message);
        GameResponse response = new GameResponse();
        if (room.notifyWhenReady(message.getPlayerId())) {
            response.setBegin(true);
            response.setPlayers(room.getPlayers());
        } else {
            room.notifyWhenReady(message.getPlayerId());
            response.setPlayers(room.getPlayers());
        }
        return response;
    }

    @MessageMapping("/getQuestion")
    private GameResponse getQuestion(@Payload QuestionMessage questionMessage, Principal user, @Header("simpSessionId") String sessionId) throws Exception {
        return null;
    }

    /**
     * Door id meegeven kunnen we ze weer in de frontend identeficeren.
     *
     * @param message
     * @return
     */
    @MessageMapping("/updateposition")
    @SendTo("/topic/level")
    //todo message x en y moeten weg
    private LevelResponse updatePosition(UpdatePositionMessage message) {
        LevelResponse response = new LevelResponse();
        FieldState nextFieldState = room.updatePosition(message.getPlayerId(), message.getX(), message.getY(), Direction.valueOf(message.getDirection()), message.getTimer());
        response.setFieldState(nextFieldState.toString());
        if (nextFieldState.toString().equals("OBSTACLE")) {
            Obstacle obstacle = new Obstacle();
            response.setQuestionToUse(obstacle.generateQuestion(room.getOperators(), room.getDifficultyQuestion()));
            response.setAnswer(obstacle.generateAnswer());
        }

        response.setId(message.getPlayerId());
        response.setPlayers(room.getPlayers());
        return response;
    }

    @MessageMapping("/removeObstacle")
    private void updateMaze(GameMessage message) {
        room.getCurrentGame().removeObstacle(message.getPlayerId());
    }

    @MessageMapping("/singleplayer")
    @SendTo("/topic/level")
    private void singlePlayerMode(){

    }
}


