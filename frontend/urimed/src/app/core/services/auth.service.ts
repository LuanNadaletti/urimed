import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, tap } from 'rxjs';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private loginUrl = 'http://localhost:8080/auth/login';

  constructor(
    private http: HttpClient,
    private router: Router,
    private toastrService: ToastrService
  ) {}

  login(username: string, password: string) {
    this.http.post<any>(this.loginUrl, { username, password }).subscribe(
      res => {
        if (res) {
          localStorage.setItem('token', username);
          this.router.navigate(['/']);
          console.log(res);
        }
      },
      error => {
        this.toastrService.error('Usuário ou senha incorretos');
        throw error;
      }
    );
  }

  logout(): void {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean {
    return localStorage.getItem('token') !== null;
  }
}
