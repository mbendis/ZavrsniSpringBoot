package hr.fer.marin.zavrsni.controller;

import hr.fer.marin.zavrsni.model.Camera;
import hr.fer.marin.zavrsni.model.Object;
import hr.fer.marin.zavrsni.service.CameraService;
import hr.fer.marin.zavrsni.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CameraController {

    @Autowired
    private CameraService cameraService;

    @Autowired
    private ObjectService objectService;

    @RequestMapping("/cameras")
    public List<Camera> getCamera(@RequestParam(value = "objectId") Integer objectId){
        return cameraService.getByObjectId(objectId);
    }

    @RequestMapping(value = "/addcamera", method = RequestMethod.POST)
    public Camera addCamera(@RequestParam(value = "objectId") Integer objectId,
                            @Valid @RequestBody Camera camera){
        Object object = objectService.getObjectById(objectId);
        camera.setObject(object);
        cameraService.add(camera);
        return camera;
    }

    @RequestMapping("/deleteCamera")
    public void deleteCamera(@RequestParam(value = "cameraId") Integer cameraId){
        cameraService.deleteById(cameraId);
    }
}
