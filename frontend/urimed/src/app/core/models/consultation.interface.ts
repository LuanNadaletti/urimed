import { Appointment } from "./appointment.interface";
import { Doctor } from "./doctor.interface";
import { Patient } from "./patient.interface";
import { SOAPNote } from "./soap-note.interface";

export interface Consultation {
    doctor: Doctor;
    patient: Patient;
    consultationDate: Date;
    soapNote: SOAPNote;
    appointment: Appointment;
}