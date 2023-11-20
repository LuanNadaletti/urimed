import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Consultation } from '../../models/consultation.interface';
import { Appointment } from '../../models/appointment.interface';
import { Router } from '@angular/router';
import { SharedService } from '../../services/shared.service';
import { ConsultationService } from '../../services/consultation.service';

@Component({
  selector: 'app-consultation',
  templateUrl: './consultation.component.html',
  styleUrls: ['./consultation.component.css'],
})
export class ConsultationComponent implements OnInit {
  consultationForm: FormGroup;
  appointment: Appointment;
  consultation: any = {};

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private sharedService: SharedService,
    private consultationService: ConsultationService,
  ) {}

  ngOnInit(): void {
    let consultation: Consultation;

    this.sharedService.dataObservable.subscribe((data) => {
      if (data) {
        this.consultation.patient = data.patient;
        this.consultation.doctor = data.doctor;
        this.consultation.consultationDate = new Date();
        this.consultation.appointment = data;
      }
    });

    this.consultationForm = this.createConsultationForm();
  }

  createConsultationForm() {
    return this.formBuilder.group({
      subjective: [''],
      objective: [''],
      assessment: [''],
      plan: [''],
    });
  }

  saveConsultation() {
    this.consultation.soapNote = this.consultationForm.value;
    
    this.consultationService.saveConsultation(this.consultation) // Call the saveConsultation() function
      .subscribe((savedConsultation) => {
        console.log(savedConsultation);
        this.router.navigate(['/prescription']);
      });
  }
}
