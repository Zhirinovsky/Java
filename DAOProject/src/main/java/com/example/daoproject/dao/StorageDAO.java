package com.example.daoproject.dao;

import com.example.daoproject.model.ProductModel;
import com.example.daoproject.model.StorageModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StorageDAO {
    private static int COUNT;
    private List<StorageModel> storages;
    {
        storages = new ArrayList<>();
        storages.add(new StorageModel(++COUNT, "Восточное", "г. Омск, ул. Красноармейская, д. 4", 1000));
        storages.add(new StorageModel(++COUNT, "Западное", "г. Омск, ул. Красноармейская, д. 7", 500));
    }

    public List<StorageModel> readlist(){
        return storages;
    }

    public StorageModel read(int id){
        return storages.stream().filter(object -> object.getId() == id).findAny().orElse(null);
    }

    public void create(StorageModel object){
        object.setId(++COUNT);
        storages.add(object);
    }

    public void update(int id, StorageModel object){
        StorageModel update = read(id);
        update.setName(object.getName());
        update.setAddress(object.getAddress());
        update.setCapacity(object.getCapacity());
    }

    public void delete(int id){
        storages.removeIf(p-> p.getId() == id);
    }

}
