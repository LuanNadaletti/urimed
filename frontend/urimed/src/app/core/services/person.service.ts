import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, map, of, throwError } from 'rxjs';
import { Role } from '../models/role.interface';
import { Doctor } from '../models/doctor.interface';
import { Patient } from '../models/patient.interface';

@Injectable({
  providedIn: 'root',
})
export class PersonService {
  private baseUrl = 'http://localhost:8080/persons';

  constructor(private http: HttpClient) {}

  getDoctorByUsername(username: string): Observable<Doctor> {
    return this.http.get<Doctor>(`${this.baseUrl}/${username}`);
  }

  getPatientByUsername(username: string): Observable<Patient> {
    return this.http.get<Patient>(`${this.baseUrl}/${username}`);
  }

  getRoleByUsername(username: string): Observable<Role> {
    return this.http.get<Role>(`${this.baseUrl}/roles/${username}`);
  }

  getLoggedPatient(): Observable<Patient> {
    const username = localStorage.getItem('token');
    if (!username) {
      throw new Error('No user logged in');
    }
    return this.getPatientByUsername(username);
  }

  isDoctor(username: string): Observable<boolean> {
    return this.getRoleByUsername(username).pipe(
        map((role: Role) => role.name === 'doctor'),
        catchError((error) => {
          console.error(error);
          return of(false);
        })
      );
  }
}
