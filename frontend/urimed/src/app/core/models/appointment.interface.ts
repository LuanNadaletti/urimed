import { Doctor } from "./doctor.interface";
import { Patient } from "./patient.interface";

export interface Appointment {
    doctor: Doctor,
    patient: Patient,
    appointmentDateTime: Date,
}