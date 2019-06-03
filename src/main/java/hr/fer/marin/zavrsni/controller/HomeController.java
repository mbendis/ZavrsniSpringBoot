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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
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

    @GetMapping("/home")
    public String index(ModelMap model){
        List<Object> objects = objectService.getAllObjects();
        List<ObjectWithPercentage> objectWithPercentages = new ArrayList<>();
        for(Object object : objects){
            objectWithPercentages.add(new ObjectWithPercentage(object, objectService.getPercetageOfTablesOccupied(object.getId())));
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
    public String statistics(ModelMap model,  @RequestParam("id") Integer objectId){
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
        model.addAttribute("tables", tables);

        tableStatusChanges.sort((o1, o2) -> (int) (o1.getTime().getTime()- o2.getTime().getTime()));

        List<TimePoint> dataTime = new ArrayList<>();


        Integer occupied = 0;
        for(TableStatusChange tsc : tableStatusChanges) {
            if (tsc.getOccupied()) {
                occupied++;
            } else {
                occupied--;
            }
            SimpleDateFormat ft =
                    new SimpleDateFormat("MM/dd/YYYY HH:mm");

            dataTime.add(new TimePoint(ft.format(tsc.getTime()), occupied ));
        }

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
