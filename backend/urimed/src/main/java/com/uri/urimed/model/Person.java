package com.uri.urimed.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Table(name = "people")
public abstract class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Integer id;

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String username;

    @NotEmpty
    @Column(nullable = false)
    private String password;

    @NotEmpty
    @Size(min = 11, max = 11)
    @Column(nullable = false, unique = true)
    private String cpf;

    @NotEmpty
    @Size(min = 3, max = 250)
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private Date birthdate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "gender_id", nullable = false)
    private Gender gender;

    @NotEmpty
    @Column(nullable = false, length = 20)
    private String phone;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false)
    private Address address;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id",  referencedColumnName = "role_id", nullable = false)
    private Role role;

    public Person(String username, String password, String cpf, String name, Date birthdate, Gender gender, String phone, Address address) {
        this.username = username;
        this.password = password;
        this.cpf = cpf;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
    }
}
