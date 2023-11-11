package com.uri.urimed.model;

import com.uri.urimed.record.SpecialtyRegistrationData;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "specialties")
public class Specialty implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "specialty_id")
        private Integer id;

        @Column(nullable = false)
        private String name;

        public Specialty(SpecialtyRegistrationData data) {
                this.name = data.name();
        }
}
