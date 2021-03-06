package com.lucas.person.dao;

import com.lucas.person.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("dao")
public class PersonDaoImp implements PersonDao {

    private static List<Person> personList = new ArrayList<>();

    @Override
    public String insertPerson(UUID id, Person person) {
        personList.add(new Person(id, person.getName()));
        return "success";
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return personList.stream().filter(person -> id.equals(person.getId())).findFirst();
    }

    @Override
    public List<Person> selectAllPeople() {
        return personList;
    }

    @Override
    public String deletePerson(UUID id) {
        Optional<Person> personOptional = selectPersonById(id);
        if(!personOptional.isEmpty()){
            personList.remove(personOptional.get());
            return "success";
        }
        return "fail";
    }

    @Override
    public String updatePerson(UUID id, Person person) {
        return selectPersonById(id)
                .map(p -> {
                    int indexOfPersonToUpdate = personList.indexOf(p);
                    if (indexOfPersonToUpdate >= 0) {
                        personList.set(indexOfPersonToUpdate, new Person(id, person.getName()));
                        return "success";
                    }
                    return "fail";
                })
                .orElse(null);
    }
}
