package com.example.dbproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity()
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank()
    private String lastName;
    @NotBlank()
    private String name;
    @Size(min = 0, max = 300)
    private String middleName;
    @NotBlank()
    private String gender;
    @Email(message = "Email should be valid")
    @NotBlank()
    private String email;
    @NotBlank()
    private String password;

    public Staff(){}

    public Staff(int id, String lastName, String name, String middleName, String gender, String email, String password) {
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.middleName = middleName;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middle_name) {
        this.middleName = middle_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
}
