package com.example.dbproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity()
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "У продукта должен быть номер")
    private String number;
    @NotBlank(message = "У продукта должно быть название")
    private String name;
    @Min(value =0, message = "Цена должна быть положительной")
    private double price;
    @Min(value =0, message = "Количество должно быть положительным")
    private int amount;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Storage storage;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Supplier supplier;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Event event;

    public Product(){}

    public Product(int id, String number, String name, double price, int amount, Storage storage, Supplier supplier, Event event) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.storage = storage;
        this.supplier = supplier;
        this.event = event;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
