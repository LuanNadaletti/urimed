package com.uri.urimed.repository;

import com.uri.urimed.model.Doctor;
import com.uri.urimed.model.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Integer>, JpaSpecificationExecutor<DoctorAvailability> {

    @Query("SELECT da FROM DoctorAvailability da WHERE da.doctor = :doctor AND da.dayOfWeek = :dayOfWeek AND :time BETWEEN da.startTime AND da.endTime")
    DoctorAvailability findByDoctorAndDayOfWeekAndTimeBetween(@Param("doctor") Doctor doctor, @Param("dayOfWeek") String dayOfWeek, @Param("time") LocalTime time);
}
