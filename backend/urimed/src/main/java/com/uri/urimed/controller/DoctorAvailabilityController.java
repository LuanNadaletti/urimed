package com.uri.urimed.controller;

import com.uri.urimed.model.DoctorAvailability;
import com.uri.urimed.repository.DoctorAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor-availabilities")
public class DoctorAvailabilityController {

    @Autowired
    private DoctorAvailabilityRepository doctorAvailabilityRepository;

    @PostMapping("/save-all")
    public ResponseEntity<DoctorAvailability> saveAll(@RequestBody List<DoctorAvailability> doctorAvailabilities) {
        doctorAvailabilityRepository.saveAll(doctorAvailabilities);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<DoctorAvailability>> getAll(Specification<DoctorAvailability> specification) {
        return ResponseEntity.ok(doctorAvailabilityRepository.findAll(specification));
    }
}
