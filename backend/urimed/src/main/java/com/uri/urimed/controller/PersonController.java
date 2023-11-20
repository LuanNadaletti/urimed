package com.uri.urimed.controller;

import com.uri.urimed.model.Person;
import com.uri.urimed.model.Role;
import com.uri.urimed.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/{username}")
    public Person findPersonByUsername(@PathVariable("username") String username) {
        return personRepository.findPersonByUsername(username);
    }

    @GetMapping("/roles/{username}")
    public Role findPersonRoleByUsername(@PathVariable("username") String username) {
        return personRepository.findPersonRoleByUsername(username);
    }
}
