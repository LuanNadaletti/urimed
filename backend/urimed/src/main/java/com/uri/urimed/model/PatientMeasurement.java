package com.uri.urimed.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient_measurements")
public class PatientMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_measurement_id")
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @NotNull
    @PastOrPresent
    @Column(name = "measurement_date")
    private LocalDateTime measurementDate;

    @NotNull
    @Positive
    @Column(name = "blood_pressure")
    private Double bloodPressure;

    @NotNull
    @Positive
    @Column(name = "heart_rate")
    private Double heartRate;

    @NotNull
    @Positive
    private Double temperature;

    @NotNull
    @Positive
    private Double weight;

    @NotNull
    @Positive
    private Double height;
}
