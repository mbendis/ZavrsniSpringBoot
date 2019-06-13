package hr.fer.marin.zavrsni.controller;

import hr.fer.marin.zavrsni.model.Camera;
import hr.fer.marin.zavrsni.model.Table;
import hr.fer.marin.zavrsni.model.TableStatusChange;
import hr.fer.marin.zavrsni.service.CameraService;
import hr.fer.marin.zavrsni.service.TableService;
import hr.fer.marin.zavrsni.service.TableStatusChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TableController {

    @Autowired
    private TableStatusChangeService tableStatusChangeService;

    @Autowired
    private TableService tableService;

    @Autowired
    private CameraService cameraService;

    @RequestMapping("/tables")
    public List<Table> getTables(@RequestParam(value = "cameraId") int id){
        return tableService.getByCameraId(id);
    }

    @RequestMapping(value = "/tables", method = RequestMethod.POST)
    public Table addTable(@RequestParam(value = "cameraId") Integer cameraId,
                           @Valid @RequestBody Table table
    ){
        Camera camera = cameraService.getById(cameraId);
        table.setCamera(camera);
        table.setOccupied(false);
        return tableService.add(table);
    }

    @RequestMapping(value = "/tables", method = RequestMethod.DELETE)
    public String deleteTable(@RequestParam(value = "tableId") Integer tableId){
        List<TableStatusChange> tableStatusChanges = tableStatusChangeService.getAllByTableId(tableId);
        for(TableStatusChange tsc : tableStatusChanges){
            tsc.setTable(null);
        }
        tableService.deleteById(tableId);
        return "Succesfuly deleted table " + tableId;
    }
}
