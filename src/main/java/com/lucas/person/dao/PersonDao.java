package com.lucas.person.dao;

import com.lucas.person.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    String insertPerson(UUID id, Person person);

    default String insertPerson(Person person){
        if(person.getId() != null){
            return insertPerson(person.getId(), person);
        }
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }
    Optional<Person> selectPersonById(UUID id);
    List<Person> selectAllPeople();
    String deletePerson(UUID id);
    String updatePerson(UUID id, Person person);

}
