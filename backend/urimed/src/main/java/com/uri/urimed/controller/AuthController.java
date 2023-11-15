package com.uri.urimed.controller;

import com.uri.urimed.model.Person;
import com.uri.urimed.record.LoginRequest;
import com.uri.urimed.repository.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/login")
    public ResponseEntity<Person> login(@RequestBody @Valid LoginRequest login) {
        Person person = personRepository.findPersonByUsername(login.username());
        if (person == null) {
            return ResponseEntity.notFound().build();
        }

        if (!person.getPassword().equals(login.password())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(person);
    }

}
