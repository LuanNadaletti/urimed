package com.uri.urimed.model;

import com.uri.urimed.record.DoctorRegistrationData;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String crm;

    @Column(nullable = false)
    private String email;

    public Doctor(DoctorRegistrationData data) {
        this.name = data.name();
        this.crm = data.crm();
        this.email = data.email();
    }

}
