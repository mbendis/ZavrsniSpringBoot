package hr.fer.marin.zavrsni.controller;

import hr.fer.marin.zavrsni.model.Camera;
import hr.fer.marin.zavrsni.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CameraController {

    @Autowired
    private CameraService cameraService;

    @RequestMapping("/cameras")
    public List<Camera> getCamera(){
        return cameraService.getAll();
    }
}
