package hr.fer.marin.zavrsni.controller;

import hr.fer.marin.zavrsni.model.Camera;
import hr.fer.marin.zavrsni.model.Object;
import hr.fer.marin.zavrsni.model.Table;
import hr.fer.marin.zavrsni.service.CameraService;
import hr.fer.marin.zavrsni.service.ObjectService;
import hr.fer.marin.zavrsni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CameraService cameraService;

    @Autowired
    ObjectService objectService;

    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String index(ModelMap model){
        List<Object> objects = objectService.getAllObjects();
        model.addAttribute("objects", objects);
        return "/home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "registration";
    }

    @GetMapping("/myobjects")
    public String myObjects(Principal principal, ModelMap model){
        String username = principal.getName();
        Integer id = userService.findUserByUsername(username).getId();
        List<Object> objects = objectService.findByUserId(id);
        model.addAttribute("objects", objects);
        return "myobjects";
    }

    @GetMapping("/createobject")
    public String createobject() {
        return "createobject";
    }

    @GetMapping("/statistics")
    public String statistics(){
        return "statistics";
    }

    @ModelAttribute("cameras")
    public List<Camera> getCamerasForObject(Integer objectId){
        return cameraService.getByObjectId(objectId);
    }

    @RequestMapping("/showContentPart")
    public String showContentPart() {
        return "content-part";
    }

}
