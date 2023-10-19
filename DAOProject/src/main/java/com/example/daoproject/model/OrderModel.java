package com.example.daoproject.model;

public class OrderModel {

    private int id;
    private String name;
    private String date;
    private double price;
    private String address;

    public OrderModel(){}

    public OrderModel(int id, String name, String date, double price, String address) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.price = price;
        this.address = address;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
