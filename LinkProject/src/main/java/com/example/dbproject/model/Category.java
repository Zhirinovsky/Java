package com.example.dbproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Collection;
import java.util.List;

@Entity()
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "У категории должно быть указано название")
    private String name;

    @ManyToMany
    @JoinTable (name="compliance",
            joinColumns=@JoinColumn (name="category_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    private List<Product> products;

    public Category(){}

    public Category(int id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
