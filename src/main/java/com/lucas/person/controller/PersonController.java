package com.lucas.person.controller;

import com.lucas.person.model.Person;
import com.lucas.person.service.PersonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @ApiOperation(value="create user", notes="")
    @PostMapping
    public String addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }

    @ApiOperation(value="get all users", notes="")
    @GetMapping
    public List<Person> getAllPerson(){
        return personService.getAllPerson();
    }

    @ApiOperation(value="get user", notes="")
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable UUID id){
        return personService.getPersonById(id).orElse(null);
    }

    @ApiOperation(value="delete user", notes="")
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable UUID id){
        return personService.deletePerson(id);
    }

    @ApiOperation(value="update user", notes="")
    @PutMapping("/{id}")
    public String updatePerson(@PathVariable UUID id, @RequestBody Person person){
        return personService.updatePerson(id, person);
    }
}
