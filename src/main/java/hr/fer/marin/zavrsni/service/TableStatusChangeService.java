package hr.fer.marin.zavrsni.service;

import hr.fer.marin.zavrsni.model.Table;
import hr.fer.marin.zavrsni.model.TableStatusChange;
import hr.fer.marin.zavrsni.model.TimePoint;
import hr.fer.marin.zavrsni.repository.TableStatusChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TableStatusChangeService {

    @Autowired
    private TableStatusChangeRepository tableStatusChangeRepository;

    @Autowired
    private CameraService cameraService;

    @Autowired
    private  TableService tableService;

    public List<TableStatusChange> getAll(){
        return tableStatusChangeRepository.findAll();
    }

    public TableStatusChange getById(Integer id){
        return tableStatusChangeRepository.getOne(id);
    }

    public TableStatusChange add(TableStatusChange tableStatusChange){
        return tableStatusChangeRepository.save(tableStatusChange);
    }

    public void deleteById(Integer id){
        tableStatusChangeRepository.deleteById(id);
    }

    public List<TableStatusChange> getAllByTableId(Integer tableId){
        return tableStatusChangeRepository.findAllByTableId(tableId);
    }

    public List<TimePoint> getTimeframeByObjectId(Integer objectId, String timeframe){

        List<Table> tables = tableService.getTablesByObjectId(objectId);
        List<TableStatusChange> tableStatusChanges = new ArrayList<>();
        List<TimePoint> dataTime = new ArrayList<>();

        for(Table table : tables){
            if(timeframe.equals("all")){
                tableStatusChanges.addAll(getAllByTableId(table.getId()));
            } else {
                for (TableStatusChange tsc : getAllByTableId(table.getId())) {
                    if (timeframe.equals("day") && tsc.getTime().getDate() == new Date().getDate()) {
                        tableStatusChanges.add(tsc);
                    } else if (timeframe.equals("week") && tsc.getTime().getDate() > (new Date().getDate() - 7)) {
                        tableStatusChanges.add(tsc);
                    } else if (timeframe.equals("month") && tsc.getTime().getMonth() == new Date().getMonth()) {
                        tableStatusChanges.add(tsc);
                    }
                }
            }
        }

        tableStatusChanges.sort((o1, o2) -> (int) (o1.getTime().getTime()- o2.getTime().getTime()));

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
        return dataTime;
    }

}
