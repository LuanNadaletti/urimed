import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DoctorAvailability } from '../models/doctor-availability.interface';

@Injectable({
  providedIn: 'root',
})
export class DoctorAvailabilityService {
  private baseUrl = 'http://localhost:8080/doctor-availabilities';

  constructor(private http: HttpClient) {}

  createAll(doctorAvailabilities: DoctorAvailability[]): Observable<any> {
    return this.http.post(this.baseUrl + "/save-all", doctorAvailabilities);
  }
}
