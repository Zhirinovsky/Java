package com.example.dbproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;

@Entity()
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Нужно указать адрес хранилища")
    private String address;
    @NotNull(message = "Нужно указать вместимость склада")
    @Min(value = 0, message = "Вместимость должна быть положительной")
    private int capacity;
    @OneToMany (mappedBy = "storage", fetch = FetchType.EAGER)
    private Collection<Product> tenants;

    public Storage(){}

    public Storage(int id, String address, int capacity, Collection<Product> tenants) {
        this.id = id;
        this.address = address;
        this.capacity = capacity;
        this.tenants = tenants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Collection<Product> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<Product> tenants) {
        this.tenants = tenants;
    }
}
