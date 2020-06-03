package service.restcontroller;

import Entities.DalUser;
import Implementations.UserToDataBase;
import Logic.Models.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
public class AccountController {

    private DalUser dalUser;
    private UserToDataBase userToDataBase = new UserToDataBase();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public DalUser login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        dalUser = userToDataBase.read(username, password);
        if (dalUser == null) {
            dalUser = new DalUser(-1, null, null, null, null, false, -1, -1, null);
        }
        return dalUser;
    }

    //todo postmapping
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public DalUser register(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, @RequestParam(value = "teacherId") Boolean teacherId, @RequestParam(value = "avatarUrl") String avatarUrl, @RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName) {

        userToDataBase.create(new DalUser(username, password, teacherId, avatarUrl, firstName, lastName));
        dalUser = userToDataBase.read(username, password);
        System.err.println(dalUser);
        return dalUser;
    }

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }
}