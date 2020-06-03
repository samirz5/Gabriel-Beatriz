package service.restcontroller;

import Logic.Enums.FieldState;
import Logic.Enums.MazeDifficulty;
import Logic.GamePackage.Maze;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MazeController {

    @RequestMapping("/maze")
    public FieldState[][] getMaze(@RequestParam(value="difficulty", defaultValue = "MEDIUM") String difficulty){
        FieldState[][] maze = Maze.generateMaze((MazeDifficulty.valueOf(difficulty)));
        return maze;
    }

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
    }
}
