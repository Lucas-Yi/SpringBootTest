package com.lucas.person;

import com.lucas.person.controller.PersonController;
import com.lucas.person.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class PersonControllerNotNullTest {

    @Autowired
    private PersonController personController;

    @Test
    public void contextLoads() throws Exception{
        assertThat(personController).isNotNull();
    }
}
