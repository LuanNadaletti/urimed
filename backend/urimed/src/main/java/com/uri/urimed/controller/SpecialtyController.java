package com.uri.urimed.controller;

import com.uri.urimed.model.Specialty;
import com.uri.urimed.repository.SpecialtyRepository;
import com.uri.urimed.util.ListUtils;
import jakarta.validation.Valid;
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
@RequestMapping("specialties")
public class SpecialtyController {

    private final SpecialtyRepository specialtyRepository;

    @Autowired
    public SpecialtyController(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @PostMapping
    public ResponseEntity<Specialty> save(@RequestBody @Valid Specialty specialty) {
        Specialty persistedSpecialty = specialtyRepository.save(specialty);
        URI location = URI.create("/specialties/" + persistedSpecialty.getId());

        return ResponseEntity.created(location).body(persistedSpecialty);
    }

    @GetMapping
    public ResponseEntity<List<Specialty>> getAllSpecialties() {
        List<Specialty> specialties = specialtyRepository.findAll();
        if (ListUtils.isNullOrEmpty(specialties)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(specialties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specialty> getSpecialtyById(@PathVariable("id") Integer id) {
        return specialtyRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Specialty> delete(@PathVariable("id") Integer id) {
        try {
            specialtyRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
