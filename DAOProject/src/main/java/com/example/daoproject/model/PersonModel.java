package com.example.daoproject.model;

public class PersonModel {

    private int id;
    private String name;
    private int age;
    private int passportSerial;
    private int passportNumber;

    public  PersonModel(){}

    public PersonModel(int id, String name, int age, int passportSerial, int passportNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.passportSerial = passportSerial;
        this.passportNumber = passportNumber;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPassportSerial() {
        return passportSerial;
    }

    public void setPassportSerial(int passportSerial) {
        this.passportSerial = passportSerial;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }
}
