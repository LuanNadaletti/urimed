import { Component, OnInit } from '@angular/core';
import { DoctorService } from '../../services/doctor.service';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Doctor } from 'src/app/core/models/doctor.interface';
import { ErrorHandlingService } from 'src/app/core/services/errorhandling.service';
import { ValidationService } from 'src/app/core/services/validation.service';
import { ToastrService } from 'ngx-toastr';
import { Specialty } from 'src/app/core/models/specialty.interface';
import { SpecialtyService } from '../../services/specialty.service';
import { Gender } from 'src/app/core/models/gender.interface';
import { GenderService } from 'src/app/core/services/gender.service';

@Component({
  selector: 'app-doctor-registration',
  templateUrl: './doctor-registration.component.html',
  styleUrls: ['./doctor-registration.component.css'],
})
export class DoctorRegistrationComponent implements OnInit {
  specialties: Specialty[];
  genders: Gender[] = [];
  doctorForm: FormGroup;

  constructor(
    private doctorService: DoctorService,
    private specialtyService: SpecialtyService,
    private genderService: GenderService,
    private toastrService: ToastrService,
    private formBuilder: FormBuilder,
    private errorHandlingService: ErrorHandlingService,
    private validationService: ValidationService
  ) {}

  ngOnInit(): void {
    this.doctorForm = this.createDoctorForm();
    this.specialtyService.getEspecialties().subscribe((specialties) => {
      this.specialties = specialties.map((specialty: Specialty) => {
        return {
          id: specialty.id,
          name: specialty.name,
        };
      });
    });

    this.genderService.getGenders().subscribe((genders) => {
      this.genders = genders.map((gender: Gender) => {
        return {
          id: gender.id,
          name: gender.name,
        };
      });
    });
  }

  saveDoctor() {
    this.doctorForm.markAllAsTouched();

    if (!this.doctorForm.valid) {
      this.errorHandlingService.handleFormGroupErrors(this.doctorForm);
      return;
    }

    this.doctorService.createDoctor(this.createDoctor()).subscribe(
      (res) => {
        this.toastrService.success('Médico cadastrado com sucesso', 'Sucesso');
        this.doctorForm.reset();
      },
      (err) => {
        this.toastrService.error('Não foi possível cadastrar o médico', 'Erro');
      }
    );

    this.clearDoctor();
  }

  createDoctorForm(): FormGroup {
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
      CRM: new FormControl('', [Validators.required]),
      Email: new FormControl('', [Validators.required]),
      Especialidade: new FormControl('', [Validators.required]),
    });
  }

  createDoctor(): Doctor {
    return {
      cpf: this.doctorForm.get('CPF')?.value,
      name: this.doctorForm.get('Nome')?.value,
      birthdate: this.doctorForm.get('DataNascimento')?.value,
      gender: this.doctorForm.get('Gênero')?.value,
      phone: this.doctorForm.get('Telefone')?.value,
      address: {
        city: this.doctorForm.get('Endereço.Cidade')?.value,
        neighborhood: this.doctorForm.get('Endereço.Bairro')?.value,
        street: this.doctorForm.get('Endereço.Rua')?.value,
        number: this.doctorForm.get('Endereço.Número')?.value,
        state: this.doctorForm.get('Endereço.Estado')?.value,
        zipCode: this.doctorForm.get('Endereço.CEP')?.value,
      },
      crm: this.doctorForm.get('CRM')?.value,
      email: this.doctorForm.get('Email')?.value,
      specialty: this.doctorForm.get('Especialidade')?.value,
      username: this.doctorForm.get('CPF')?.value,
      password: this.doctorForm.get('CPF')?.value,
      role: {
        id: 2,
        name: 'doctor',
      }
    };
  }

  clearDoctor() {
    this.doctorForm = this.createDoctorForm();
  }
}
