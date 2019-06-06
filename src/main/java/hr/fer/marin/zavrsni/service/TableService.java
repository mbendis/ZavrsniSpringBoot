package hr.fer.marin.zavrsni.service;

import hr.fer.marin.zavrsni.model.Camera;
import hr.fer.marin.zavrsni.model.Table;
import hr.fer.marin.zavrsni.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private CameraService cameraService;

    public Table getById(Integer id){
        return tableRepository.getOne(id);
    }

    public Table add(Table table){
        return tableRepository.save(table);
    }

    public void deleteById(Integer id){
        tableRepository.deleteById(id);
    }

    public List<Table> getByCameraId(Integer cameraId){
        return tableRepository.findByCameraId(cameraId);
    }

    public Table update(Table table){
        return tableRepository.save(table);
    }

    public List<Table> getTablesByObjectId(Integer objectId){
        List<Camera> cameras = cameraService.getByObjectId(objectId);
        List<Table> tables = new ArrayList<>();
        for (Camera camera: cameras) {
            tables.addAll(getByCameraId(camera.getId()));
        }
        return tables;
    }

    public Integer getNumberOfOccupiedInObject(Integer objectId){
        return getTablesByObjectId(objectId).stream().filter(Table::getOccupied).collect(toList()).size();
    }
}
