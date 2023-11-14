package com.uri.urimed.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * Represents a Doctor in the system.
 * <p>
 * This class is a JPA entity that maps to the "doctors" table in the database.
 * It extends the {@link Person} class and includes additional fields for the doctor's CRM, email, and specialty.
 * </p>
 * <p>
 * This class implements {@link java.io.Serializable}, which means it can be converted to a byte stream and
 * restored without losing the type of its fields and the relationships between the fields.
 * </p>
 *
 * @author Luan Nadaletti
 * @version 1.0
 * @see Person
 */
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

    public Doctor(String cpf, String name, Date birthdate, Gender gender, String phone, Address address, String crm, String email, Specialty specialty) {
        super(cpf, name, birthdate, gender, phone, address);
        this.crm = crm;
        this.email = email;
        this.specialty = specialty;
    }
}
