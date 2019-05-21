package hr.fer.marin.zavrsni.controller;

import hr.fer.marin.zavrsni.model.User;
import hr.fer.marin.zavrsni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public List<User> allUsers(){
        return userService.getAll();
    }

    @RequestMapping("/hello")
    public String hello(){
        return "Hello";
    }
}
