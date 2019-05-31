package hr.fer.marin.zavrsni.service;

import hr.fer.marin.zavrsni.model.TableStatusChange;
import hr.fer.marin.zavrsni.repository.TableStatusChangeRepository;
import javafx.scene.control.Tab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableStatusChangeService {

    @Autowired
    private TableStatusChangeRepository tableStatusChangeRepository;

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
}
