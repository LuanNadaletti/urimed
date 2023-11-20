package com.uri.urimed.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "soap_notes")
public class SOAPNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "soap_note_id")
    private Integer id;

    @Column(length = 5000)
    private String subjective;

    @Column(length = 5000)
    private String objective;

    @Column(length = 5000)
    private String assessment;

    @Column(length = 5000)
    private String plan;
}
