package com.uri.urimed.repository;

import com.uri.urimed.model.Doctor;
import com.uri.urimed.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>, JpaSpecificationExecutor<Doctor> {

    List<Doctor> findBySpecialty(Specialty specialty);
}
