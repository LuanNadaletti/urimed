package com.uri.urimed.model;

import com.uri.urimed.record.DoctorRegistrationData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "doctors")
public class Doctor extends Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 20)
    private String crm;

    @Column(nullable = false, length = 250)
    private String email;

    @ManyToOne
    @JoinColumn(name = "specialty_id", referencedColumnName = "specialty_id", nullable = false)
    private Specialty specialty;

    public Doctor(@NotNull DoctorRegistrationData data) {
        super(data.cpf(), data.name(), data.birthdate(), data.gender(), data.phone(), data.address());
        this.crm = data.crm();
        this.email = data.email();
        this.specialty = data.specialty();
    }

}
