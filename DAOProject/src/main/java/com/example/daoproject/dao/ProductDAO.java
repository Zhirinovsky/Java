package com.example.daoproject.dao;

import com.example.daoproject.model.PersonModel;
import com.example.daoproject.model.ProductModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAO {
    private static int COUNT;
    private List<ProductModel> products;
    {
        products = new ArrayList<>();
        products.add(new ProductModel(++COUNT, "A1001", "Ручка", 25.00, 20));
        products.add(new ProductModel(++COUNT, "A1002", "Карандаш", 15.00, 150));
        products.add(new ProductModel(++COUNT, "A1003", "Степлер", 100.00, 10));
        products.add(new ProductModel(++COUNT, "A1004", "Бутерброд", 50.00, 50));
    }

    public List<ProductModel> readlist(){
        return products;
    }

    public ProductModel read(int id){
        return products.stream().filter(object -> object.getId() == id).findAny().orElse(null);
    }

    public void create(ProductModel object){
        object.setId(++COUNT);
        products.add(object);
    }

    public void update(int id, ProductModel object){
        ProductModel update = read(id);
        update.setNumber(object.getNumber());
        update.setName(object.getName());
        update.setAmount(object.getAmount());
        update.setPrice(object.getPrice());
    }

    public void delete(int id){
        products.removeIf(p-> p.getId() == id);
    }

}
