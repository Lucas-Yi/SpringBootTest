package com.lucas.person.dao;

import com.lucas.person.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }
    Optional<Person> selectPersonById(UUID id);
    List<Person> selectAllPeople();
    int deletePerson(UUID id);
    int updatePerson(UUID id, Person person);

}
