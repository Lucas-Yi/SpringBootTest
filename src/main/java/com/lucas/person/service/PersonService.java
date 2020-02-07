package com.lucas.person.service;

import com.lucas.person.dao.PersonDao;
import com.lucas.person.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    @Autowired
    private PersonDao personDao;

    public int addPerson(Person person){
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPerson(){
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID uuid){
        return personDao.selectPersonById(uuid);
    }

    public int deletePerson(UUID uuid){
        return personDao.deletePerson(uuid);
    }

    public int updatePerson(UUID uuid, Person person){
        return personDao.updatePerson(uuid, person);
    }

}
