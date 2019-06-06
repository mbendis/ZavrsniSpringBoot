package hr.fer.marin.zavrsni.controller;

import hr.fer.marin.zavrsni.model.Object;
import hr.fer.marin.zavrsni.model.User;
import hr.fer.marin.zavrsni.service.ObjectService;
import hr.fer.marin.zavrsni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
public class ObjectController {

    @Autowired
    UserService userService;

    @Autowired
    ObjectService objectService;

    @RequestMapping("/getobjects")
    public List<Object> getObjects(Principal principal){
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        return objectService.findByUserId(user.getId());
    }


    @RequestMapping(value = "/addobject", method = RequestMethod.POST)
    public void addObject(Principal principal,
                            @Valid Object object, HttpServletResponse response) throws IOException {
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        object.setUser(user);
        objectService.addObject(object);
        response.sendRedirect("/myobjects?successfulyAdded=true");
    }

    @RequestMapping(value = "/deleteobject", method = RequestMethod.POST)
    public void deleteObject(@RequestParam("id") Integer objectId, HttpServletResponse response) throws IOException {
        objectService.deleteById(objectId);
        response.sendRedirect("/myobjects?successfulyDeleted=true");
    }
}
