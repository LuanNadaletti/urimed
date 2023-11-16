import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SpecialtyService {
  private apiUrl = 'http://localhost:8080/specialties';

  constructor(private http: HttpClient) {}

  getEspecialties(): Observable<any> {
    console.log(this.http.get(this.apiUrl));
    return this.http.get(this.apiUrl);
  }
}
