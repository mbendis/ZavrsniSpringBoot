package hr.fer.marin.zavrsni.service;

import hr.fer.marin.zavrsni.model.Camera;
import hr.fer.marin.zavrsni.repository.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CameraService {

    @Autowired
    private CameraRepository cameraRepository;

    public List<Camera> getAll(){
        return cameraRepository.findAll();
    }

    public Camera getById(Long id){
        return cameraRepository.getOne(id);
    }

    public Camera add(Camera camera){
        return cameraRepository.save(camera);
    }

    public void deleteById(Long id){
        cameraRepository.deleteById(id);
    }
}
