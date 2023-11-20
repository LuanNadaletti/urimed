import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ScheduleRequest } from '../models/schedule-request.interface';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  private apiUrl = 'http://localhost:8080/appointments';
  constructor(private http: HttpClient) { }

  getAppointmentsForToday(username: any): Observable<any> {
    const today = new Date();
    return this.http.get(`${this.apiUrl}/doctor/${username}/date/${today}`);
  }

  scheduleAppointment(scheduleRequest: ScheduleRequest) {
    return this.http.post(`${this.apiUrl}/schedule`, scheduleRequest);
  }
}