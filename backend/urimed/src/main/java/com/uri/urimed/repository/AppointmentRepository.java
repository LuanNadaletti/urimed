package com.uri.urimed.repository;

import com.uri.urimed.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByDoctorUsernameAndAppointmentDateTimeBetween(String username, Date start, Date end);
}