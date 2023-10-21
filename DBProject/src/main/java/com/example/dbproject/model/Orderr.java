package com.example.dbproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.sql.Date;

@Entity()
public class Orderr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank()
    private int number;
    @NotBlank()
    private Date date;
    @NotBlank()
    private boolean payment;

    public Orderr(){}

    public Orderr(int id, int number, Date date, boolean payment) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.payment = payment;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date_of_making) {
        this.date = date_of_making;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }
}
