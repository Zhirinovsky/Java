package com.example.daoproject.model;

import java.util.Date;

public class BankModel {

    private int id;
    private String name;
    private double money;
    private String concurrency;
    private String address;

    public BankModel() {

    }

    public BankModel(int id, String name, double money, String concurrency, String address) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.concurrency = concurrency;
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(String concurrency) {
        this.concurrency = concurrency;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
