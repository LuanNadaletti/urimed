import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Consultation } from '../models/consultation.interface';

@Injectable({
  providedIn: 'root'
})
export class ConsultationService {
  private apiUrl = 'http://localhost:8080/consultations'; // Replace with your API URL

  constructor(private http: HttpClient) { }

  saveConsultation(consultation: Consultation): Observable<Consultation> {
    return this.http.post<Consultation>(this.apiUrl, consultation);
  }
}