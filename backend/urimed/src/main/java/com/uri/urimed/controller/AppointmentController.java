package com.uri.urimed.controller;

import com.uri.urimed.model.Appointment;
import com.uri.urimed.model.Doctor;
import com.uri.urimed.model.DoctorAvailability;
import com.uri.urimed.model.Specialty;
import com.uri.urimed.record.ScheduleRequest;
import com.uri.urimed.repository.AppointmentRepository;
import com.uri.urimed.repository.DoctorAvailabilityRepository;
import com.uri.urimed.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorAvailabilityRepository doctorAvailabilityRepository;

    @GetMapping("/doctor/{username}/date/{date}")
    public List<Appointment> findAppointmentsByDoctorAndDate(@PathVariable("username") String username,
                                                             @PathVariable("date") Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date oneDayBefore = calendar.getTime();

        return appointmentRepository.findByDoctorUsernameAndAppointmentDateTimeBetween(username, oneDayBefore, date);
    }

    @PostMapping("/schedule")
    public ResponseEntity<Appointment> scheduleConsultation(@RequestBody ScheduleRequest scheduleRequest) {
        List<Doctor> doctors = doctorRepository.findBySpecialty(scheduleRequest.specialty());
        Doctor doctor = doctors.get(0);
        DoctorAvailability doctorAvailability = doctorAvailabilityRepository.findByDoctorAndDayOfWeekAndTimeBetween(doctor, scheduleRequest.dayOfWeek(), scheduleRequest.startTime());

        Appointment appointment = new Appointment();
        if (doctorAvailability != null) {
            appointment.setPatient(scheduleRequest.patient());
            appointment.setDoctor(doctor);
            appointment.setAppointmentDateTime(scheduleRequest.date());
            appointmentRepository.save(appointment);
            return ResponseEntity.ok(appointment);
        }

        appointmentRepository.save(appointment);
        return ResponseEntity.notFound().build();
    }
}