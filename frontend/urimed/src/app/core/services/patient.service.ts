import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Patient } from '../models/patient.interface';

@Injectable({
  providedIn: 'root',
})
export class PatientService {
  private baseUrl = 'http://localhost:8080/patients';

  constructor(private http: HttpClient) {}

  createPatient(patient: Patient): Observable<any> {
    return this.http.post(this.baseUrl, patient);
  }
}
