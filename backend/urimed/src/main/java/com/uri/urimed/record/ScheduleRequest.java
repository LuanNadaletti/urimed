package com.uri.urimed.record;

import com.uri.urimed.model.Patient;
import com.uri.urimed.model.Specialty;

import java.time.LocalTime;
import java.util.Date;

public record ScheduleRequest(Patient patient, Specialty specialty, Date date, String dayOfWeek, LocalTime startTime, LocalTime endTime) {
}
