package com.example.dbproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity()
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 11, max = 11)
    private String phone;
    @NotBlank()
    private String email;
    @NotBlank()
    private String password;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    private Discount_Card discountCard;

    public Buyer(){}

    public Buyer(int id, String phone, String email, String password, Discount_Card discountCard) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.discountCard = discountCard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Discount_Card getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(Discount_Card discountCard) {
        this.discountCard = discountCard;
    }
}
