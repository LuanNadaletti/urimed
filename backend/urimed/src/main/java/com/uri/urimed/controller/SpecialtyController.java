package com.uri.urimed.controller;

import com.uri.urimed.model.Specialty;
import com.uri.urimed.record.SpecialtyRegistrationData;
import com.uri.urimed.repository.SpecialtyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("specialties")
public class SpecialtyController {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyController(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @PostMapping("save")
    public ResponseEntity<String> save(@RequestBody SpecialtyRegistrationData data) {
        Specialty specialty = new Specialty(data);
        specialtyRepository.save(specialty);

        return ResponseEntity.status(HttpStatus.CREATED).body("Specialty saved");
    }

    @PostMapping("delete")
    public ResponseEntity<String> delete(@RequestBody SpecialtyRegistrationData data) {
        Specialty specialty = new Specialty(data);
        specialtyRepository.delete(specialty);

        return ResponseEntity.status(HttpStatus.OK).body("Specialty deleted");
    }

}
