import { Component, OnInit } from '@angular/core';
import { Gender } from '../../models/gender.interface';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { PatientService } from '../../services/patient.service';
import { GenderService } from '../../services/gender.service';
import { ValidationService } from '../../services/validation.service';
import { Patient } from '../../models/patient.interface';
import { ErrorHandlingService } from '../../services/errorhandling.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-patient-registration',
  templateUrl: './patient-registration.component.html',
  styleUrls: ['./patient-registration.component.css'],
})
export class PatientRegistrationComponent implements OnInit {
  genders: Gender[];
  patientForm: FormGroup;

  constructor(
    private patientService: PatientService,
    private genderService: GenderService,
    private formBuilder: FormBuilder,
    private toastrService: ToastrService,
    private validationService: ValidationService,
    private errorHandlingService: ErrorHandlingService
  ) {}

  ngOnInit(): void {
    this.patientForm = this.createPatientForm();

    this.genderService.getGenders().subscribe((genders) => {
      this.genders = genders.map((gender: Gender) => {
        return {
          id: gender.id,
          name: gender.name,
        };
      });
    });
  }

  savePatient() {
    this.patientForm.markAllAsTouched();

    if (!this.patientForm.valid) {
      console.log(this.patientForm);
      this.errorHandlingService.handleFormGroupErrors(this.patientForm);
      return;
    }

    this.patientService.createPatient(this.createPatient()).subscribe(
      (res) => {
        this.toastrService.success('Paciente cadastrado com sucesso', 'Sucesso');
        this.patientForm.reset();
      },
      (err) => {
        this.toastrService.error('Não foi possível cadastrar o paciente', 'Erro');
      }
    );

    this.clearPatient();
  }

  createPatientForm(): FormGroup {
    return this.formBuilder.group({
      Nome: new FormControl('', [Validators.required]),
      CPF: new FormControl('', [
        Validators.required,
        Validators.minLength(11),
        Validators.maxLength(11),
        this.validationService.validateCpf,
      ]),
      DataNascimento: new FormControl('', [Validators.required]),
      Gênero: new FormControl('', [Validators.required]),
      Telefone: new FormControl('', [Validators.required]),
      Endereço: this.formBuilder.group({
        Cidade: new FormControl('', [Validators.required]),
        Bairro: new FormControl('', [Validators.required]),
        Rua: new FormControl('', [Validators.required]),
        Número: new FormControl('', [Validators.required]),
        Estado: new FormControl('', [Validators.required]),
        CEP: new FormControl('', [Validators.required]),
      }),
      Email: new FormControl('', [Validators.required]),
    });
  }

  createPatient(): Patient {
    return {
      cpf: this.patientForm.get('CPF')?.value,
      name: this.patientForm.get('Nome')?.value,
      birthdate: this.patientForm.get('DataNascimento')?.value,
      gender: this.patientForm.get('Gênero')?.value,
      phone: this.patientForm.get('Telefone')?.value,
      address: {
        city: this.patientForm.get('Endereço.Cidade')?.value,
        neighborhood: this.patientForm.get('Endereço.Bairro')?.value,
        street: this.patientForm.get('Endereço.Rua')?.value,
        number: this.patientForm.get('Endereço.Número')?.value,
        state: this.patientForm.get('Endereço.Estado')?.value,
        zipCode: this.patientForm.get('Endereço.CEP')?.value,
      },
      email: this.patientForm.get('Email')?.value,
      username: this.patientForm.get('CPF')?.value,
      password: this.patientForm.get('CPF')?.value,
      role: {
        id: 3,
        name: 'patient',
      }
    };
  }

  clearPatient() {
    this.patientForm = this.createPatientForm();
  }
}
