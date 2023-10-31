package com.uri.urimed.controller;

import com.uri.urimed.model.Doctor;
import com.uri.urimed.record.DoctorRegistrationData;
import com.uri.urimed.repository.DoctorRepository;
import com.uri.urimed.repository.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @PostMapping("save")
    public ResponseEntity<String> save(@RequestBody DoctorRegistrationData data) {
        try {
            Doctor doctor = new Doctor(data);
            doctorRepository.save(doctor);
        } catch (Exception e) {
            return new ResponseEntity<>("Inconsistent data", null, ResponseEntity.badRequest().build().getStatusCode());
        }

        return new ResponseEntity<>("Doctor saved", null, ResponseEntity.ok().build().getStatusCode());
    }

    @PostMapping("delete")
    public void delete(@RequestBody DoctorRegistrationData data) {
        Doctor doctor = new Doctor(data);
        doctorRepository.delete(doctor);
    }

}
