package hr.fer.marin.zavrsni.service;

import hr.fer.marin.zavrsni.model.Object;
import hr.fer.marin.zavrsni.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectService {

    @Autowired
    private ObjectRepository objectRepository;

    public List<Object> getAllObjects(){
        return objectRepository.findAll();
    }

    public Object getObjectById(Long id){
        return objectRepository.getOne(id);
    }

    public Object addObject(Object object){
        return objectRepository.save(object);
    }

    public void deleteById(Long id){
        objectRepository.deleteById(id);
    }
}
