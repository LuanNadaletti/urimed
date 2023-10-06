package com.uri.urimed.controller;

import com.uri.urimed.model.Doctor;
import com.uri.urimed.record.DoctorRegistrationData;
import com.uri.urimed.repository.DoctorRepository;
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

    @PostMapping
    public void register(@RequestBody DoctorRegistrationData data) {
        Doctor doctor = new Doctor(data);

        doctorRepository.save(doctor);
    }

}