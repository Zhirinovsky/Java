package com.example.daoproject.dao;

import com.example.daoproject.model.PersonModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int COUNT;
    private List<PersonModel> people;
    {
        people = new ArrayList<>();
        people.add(new PersonModel(++COUNT, "Михаил", 20, 8906, 345238));
        people.add(new PersonModel(++COUNT, "Алексей", 20, 4366, 546456));
        people.add(new PersonModel(++COUNT, "Святослав", 21, 1246, 345643));
        people.add(new PersonModel(++COUNT, "Правдоруб", 21, 7645, 765427));
    }

    public List<PersonModel> readlist(){
        return people;
    }

    public PersonModel read(int id){
        return people.stream().filter(personModel -> personModel.getId() == id).findAny().orElse(null);
    }

    public void create(PersonModel person){
        person.setId(++COUNT);
        people.add(person);
    }

    public void update(int id, PersonModel personModel){
        PersonModel updatePerson = read(id);
        updatePerson.setName(personModel.getName());
        updatePerson.setAge(personModel.getAge());
        updatePerson.setPassportSerial(personModel.getPassportSerial());
        updatePerson.setPassportNumber(personModel.getPassportNumber());
    }

    public void delete(int id){
        people.removeIf(p-> p.getId() == id);
    }

}
