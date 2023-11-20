package com.uri.urimed.controller;

import com.uri.urimed.model.Patient;
import com.uri.urimed.model.Role;
import com.uri.urimed.repository.PatientRepository;
import com.uri.urimed.util.ListUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<Patient> save(@RequestBody @Valid Patient patient) {
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        Patient persistedPatient = patientRepository.save(patient);
        URI location = URI.create("/patients/" + persistedPatient.getId());

        return ResponseEntity.created(location).body(persistedPatient);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        if (ListUtils.isNullOrEmpty(patients)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Integer id) {
        return patientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> delete(@PathVariable("id") Integer id) {
        try {
            patientRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
