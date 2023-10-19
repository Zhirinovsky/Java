package com.example.daoproject.dao;

import com.example.daoproject.model.OrderModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDAO {
    private static int COUNT;
    private List<OrderModel> orders;
    {
        orders = new ArrayList<>();
        orders.add(new OrderModel(++COUNT, "A1001", "17/07/2023", 1300.00, "г. Омск, ул. Красноармейская, д. 1"));
        orders.add(new OrderModel(++COUNT, "A1002", "22/07/2023", 500.00, "г. Омск, ул. Красноармейская, д. 2"));
    }

    public List<OrderModel> readlist(){
        return orders;
    }

    public OrderModel read(int id){
        return orders.stream().filter(object -> object.getId() == id).findAny().orElse(null);
    }

    public void create(OrderModel object){
        object.setId(++COUNT);
        orders.add(object);
    }

    public void update(int id, OrderModel object){
        OrderModel update = read(id);
        update.setName(object.getName());
        update.setAddress(object.getAddress());
        update.setPrice(object.getPrice());
        update.setDate(object.getDate());
    }

    public void delete(int id){
        orders.removeIf(p-> p.getId() == id);
    }

}
