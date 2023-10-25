package com.example.dbproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity()
public class Discount_Card {

    @Id
    private int id;
    @Size(min = 11, max = 11)
    private int number;
    @NotBlank()
    private int discount;
    @OneToOne(optional = true, mappedBy = "discountCard")
    private Buyer owner;

    public Discount_Card(){}

    public Discount_Card(int id, int number, int discount, Buyer owner) {
        this.id = id;
        this.number = number;
        this.discount = discount;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Buyer getOwner() {
        return owner;
    }

    public void setOwner(Buyer owner) {
        this.owner = owner;
    }
}
