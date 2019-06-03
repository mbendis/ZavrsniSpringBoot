package hr.fer.marin.zavrsni.service;

import hr.fer.marin.zavrsni.model.Camera;
import hr.fer.marin.zavrsni.model.Object;
import hr.fer.marin.zavrsni.model.Table;
import hr.fer.marin.zavrsni.model.TableStatusChange;
import hr.fer.marin.zavrsni.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjectService {

    @Autowired
    private ObjectRepository objectRepository;

    @Autowired
    private CameraService cameraService;

    @Autowired
    private TableService tableService;

    @Autowired
    private TableStatusChangeService tableStatusChangeService;

    public List<Object> getAllObjects(){
        return objectRepository.findAll();
    }

    public Object getObjectById(Integer id){
        return objectRepository.getOne(id);
    }

    public Object addObject(Object object){
        return objectRepository.save(object);
    }

    public void deleteById(Integer id){
        objectRepository.deleteById(id);
    }

    public List<Object> findByUserId(Integer userId){ return objectRepository.findByUserId(userId);}

    public List<TableStatusChange> getAllTableStatusChange(Integer objectId){
        List<TableStatusChange> tableStatusChangeList = new ArrayList<>();
        for (Camera camera: cameraService.getByObjectId(objectId)) {
            for (Table table: tableService.getByCameraId(camera.getId())) {
                tableStatusChangeList.addAll(tableStatusChangeService.getAllByTableId(table.getId()));
            }
        }
        return tableStatusChangeList;
    }

    public int getPercetageOfTablesOccupied(Integer objectId){
        int occupied = 0;
        int total = 0;
        for (Camera camera: cameraService.getByObjectId(objectId)){
            List<Table> tables = tableService.getByCameraId(camera.getId());
            total = tables.size();
            for(Table table: tables){
                if(table.getOccupied()) occupied++;
            }
        }
        return (int)((occupied/((double)total))*100);
    }
}
