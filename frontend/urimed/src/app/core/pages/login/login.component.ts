import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LoginRequest } from 'src/app/core/models/login-request.interface';
import { AuthService } from 'src/app/core/services/auth.service';
import { ErrorHandlingService } from 'src/app/core/services/errorhandling.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loginRequest: LoginRequest;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private toastrService: ToastrService,
    private errorHandling: ErrorHandlingService
  ) {}

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      Usuário: ['', Validators.required],
      Senha: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      this.errorHandling.handleFormGroupErrors(this.loginForm);
      return;
    }

    this.loginRequest = this.createLoginRequest();

    this.authService.login(this.loginRequest.username, this.loginRequest.password);
  }

  createLoginRequest(): LoginRequest {
    return {
      username: this.loginForm.value['Usuário'],
      password: this.loginForm.value['Senha'],
    };
  }
}
