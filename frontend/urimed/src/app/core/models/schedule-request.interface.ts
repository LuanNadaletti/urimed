import { Patient } from "./patient.interface";
import { Specialty } from "./specialty.interface";

export interface ScheduleRequest {
    patient: Patient;
    specialty: Specialty;
    date: Date;
    dayOfWeek: string;
    startTime: string;
    endTime: string;
}