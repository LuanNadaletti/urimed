import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Doctor } from '../models/doctor.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DoctorService {
  private baseUrl = 'http://localhost:8080/doctors';

  constructor(private http: HttpClient) {}

  createDoctor(doctor:Doctor): Observable<any> {
    return this.http.post(this.baseUrl, doctor);
  }
}
