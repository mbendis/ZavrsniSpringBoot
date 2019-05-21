package hr.fer.marin.zavrsni.service;

import hr.fer.marin.zavrsni.model.Table;
import hr.fer.marin.zavrsni.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    public Table getById(Long id){
        return tableRepository.getOne(id);
    }

    public Table add(Table table){
        return tableRepository.save(table);
    }

    public void deleteById(Long id){
        tableRepository.deleteById(id);
    }
}
