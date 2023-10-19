package com.example.daoproject.dao;

import com.example.daoproject.model.BankModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BankDAO {
    private static int COUNT;
    private List<BankModel> banks;
    {
        banks = new ArrayList<>();
        banks.add(new BankModel(++COUNT, "Обманка", 100000000, "Рубль", "г. Омск, ул. Красноармейская, д. 10"));
        banks.add(new BankModel(++COUNT, "Кредитка", 30000000, "Доллар", "г. Омск, ул. Красноармейская, д. 11"));
    }

    public List<BankModel> readlist(){
        return banks;
    }

    public BankModel read(int id){
        return banks.stream().filter(object -> object.getId() == id).findAny().orElse(null);
    }

    public void create(BankModel object){
        object.setId(++COUNT);
        banks.add(object);
    }

    public void update(int id, BankModel object){
        BankModel update = read(id);
        update.setName(object.getName());
        update.setConcurrency(object.getConcurrency());
        update.setMoney(object.getMoney());
        update.setAddress(object.getAddress());
    }

    public void delete(int id){
        banks.removeIf(p-> p.getId() == id);
    }

}
