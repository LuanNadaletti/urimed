package com.uri.urimed.controller;

import com.uri.urimed.model.Consultation;
import com.uri.urimed.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("consultations")
public class ConsultationController {

    @Autowired
    private ConsultationRepository consultationRepository;

    @PostMapping
    public ResponseEntity<Consultation> save(@RequestBody Consultation consultation) {
        Consultation persistedConsultation = consultationRepository.save(consultation);
        URI location = URI.create("/doctors/" + persistedConsultation.getId());

        return ResponseEntity.created(location).body(persistedConsultation);
    }
}