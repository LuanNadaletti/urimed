package com.uri.urimed.controller;

import com.uri.urimed.model.Gender;
import com.uri.urimed.repository.GenderRepository;
import com.uri.urimed.util.ListUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/genders")
public class GenderController {

    @Autowired
    private GenderRepository genderRepository;

    @PostMapping
    private ResponseEntity<Gender> save(@RequestBody @Valid Gender gender) {
        Gender persistedGender = genderRepository.save(gender);
        URI location = URI.create("/genders/" + persistedGender.getId());

        return ResponseEntity.created(location).body(persistedGender);
    }

    @GetMapping
    private ResponseEntity<List<Gender>> getAllGenders() {
        List<Gender> genders = genderRepository.findAll();

        if (ListUtils.isNullOrEmpty(genders)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(genders);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Gender> getGenderById(@PathVariable("id") @NotNull Integer id) {
        return genderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Gender> delete(@PathVariable("id") Integer id) {
        try {
            genderRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
