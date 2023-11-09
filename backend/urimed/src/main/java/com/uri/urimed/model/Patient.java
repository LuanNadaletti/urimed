package com.uri.urimed.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
public class Patient extends Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 10, max = 250)
    @Column
    private String email;

    public Patient(String cpf, String name, Date birthdate, String gender, String phone, Address address, String email) {
        super(cpf, name, birthdate, gender, phone, address);
        this.email = email;
    }
}
