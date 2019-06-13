package hr.fer.marin.zavrsni.controller;

import hr.fer.marin.zavrsni.model.Object;
import hr.fer.marin.zavrsni.model.*;
import hr.fer.marin.zavrsni.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    TableStatusChangeService tableStatusChangeService;

    @Autowired
    TableService tableService;

    @Autowired
    CameraService cameraService;

    @Autowired
    ObjectService objectService;

    @Autowired
    UserService userService;

    @GetMapping( value = {"/home", "/"})
    public String index(ModelMap model){
        List<Object> objects = objectService.getAllObjects();
        List<ObjectWithPercentage> objectWithPercentages = new ArrayList<>();
        for(Object object : objects){
            objectWithPercentages.add(new ObjectWithPercentage(object,
                    objectService.getPercetageOfTablesOccupied(object.getId())));
        }
        model.addAttribute("objects", objectWithPercentages);
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
    public String statistics(ModelMap model,
                             @RequestParam("id") Integer objectId,
                             @RequestParam("time") String timeframe){

        List<Camera> cameras = cameraService.getByObjectId(objectId);
        List<Table> tables = tableService.getTablesByObjectId(objectId);
        List<TimePoint> dataTime = tableStatusChangeService.getTimeframeByObjectId(objectId, timeframe);

        model.addAttribute("objectName", objectService.getObjectById(objectId).getName());
        model.addAttribute("objectId", objectId);
        model.addAttribute("numberOfOccupied", tableService.getNumberOfOccupiedInObject(objectId));
        model.addAttribute("cameras", cameras);
        model.addAttribute("tables", tables);
        model.addAttribute("datatime", dataTime);

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

    @GetMapping("/objectStats")
    public String objectStats(Principal principal, ModelMap model, @RequestParam("id") Integer objectId){

        List<Camera> cameras = cameraService.getByObjectId(objectId);
        List<Table> tables = new ArrayList<>();
        List<TableStatusChange> tableStatusChanges = new ArrayList<>();
        for (Camera camera: cameras) {
            tables.addAll(tableService.getByCameraId(camera.getId()));
        }
        model.addAttribute("cameras", cameras);

        for(Table table : tables){
            tableStatusChanges.addAll(tableStatusChangeService.getAllByTableId(table.getId()));
        }
        return "objectStats";
    }

}
