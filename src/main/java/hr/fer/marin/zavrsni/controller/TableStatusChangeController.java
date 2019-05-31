package hr.fer.marin.zavrsni.controller;

import hr.fer.marin.zavrsni.model.Table;
import hr.fer.marin.zavrsni.model.TableStatusChange;
import hr.fer.marin.zavrsni.service.TableService;
import hr.fer.marin.zavrsni.service.TableStatusChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
public class TableStatusChangeController {

    @Autowired
    private TableService tableService;

    @Autowired
    private TableStatusChangeService tableStatusChangeService;

    @RequestMapping(value = "/tablestatuschange", method = RequestMethod.POST)
    public TableStatusChange addTable(@RequestParam(value = "tableId") Integer tableId,
                                      @Valid @RequestBody TableStatusChange tableStatusChange
    ){
        Table table = tableService.getById(tableId);
        tableStatusChange.setTime(Date.from(Instant.now()));
        tableStatusChange.setTable(table);
        return tableStatusChangeService.add(tableStatusChange);
    }

    @RequestMapping("/tablestatuschange")
    public List<TableStatusChange> getTableStatusChanges(@RequestParam(value = "tableId") Integer tableId){
        return tableStatusChangeService.getAllByTableId(tableId);
    }
}
