package com.ex3.controller;

import com.ex3.entity.Person;
import com.ex3.repository.PersonRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @SchemaMapping(typeName = "Query", value = "findAll")
    public List<Person> findAll() {
        return (List<Person>) personRepository.findAll();
    }

    @SchemaMapping(typeName = "Query", value = "findById")
    public Optional<Person> findById(@Argument Integer id) {
        return personRepository.findById(id);
    }
}
