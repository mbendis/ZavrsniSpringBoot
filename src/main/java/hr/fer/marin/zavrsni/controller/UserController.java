package hr.fer.marin.zavrsni.controller;

import hr.fer.marin.zavrsni.model.User;
import hr.fer.marin.zavrsni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/allusers")
    public List<User> allUsers(){
        return userService.getAll();
    }

    @RequestMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@Valid User user, BindingResult bindingResult, HttpServletResponse response) throws IOException {
        userService.add(user);
        response.sendRedirect("/login?successfulRegistration=true");
    }
}
