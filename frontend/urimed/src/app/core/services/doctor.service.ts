import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DoctorsService {
  private baseUrl = 'http://localhost:8080'; // Substitua pela URL da sua API real

  constructor(private http: HttpClient) {}

  createDoctor(newDoctor: any) {
    const url = this.baseUrl + '/doctors';
    return this.http.post(url, newDoctor);
  }
}
