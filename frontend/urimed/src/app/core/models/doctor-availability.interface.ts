import { Time } from "@angular/common";
import { Doctor } from "./doctor.interface";

export interface DoctorAvailability {
    doctor: Doctor,
    dayOfWeek: string,
    startTime: Time,
    endTime: Time,
}