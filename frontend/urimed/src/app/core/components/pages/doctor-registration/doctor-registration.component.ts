import { Component } from '@angular/core';
import { DoctorsService } from '../../../services/doctor.service';

@Component({
  selector: 'app-doctor-registration',
  templateUrl: './doctor-registration.component.html',
  styleUrls: ['./doctor-registration.component.css'],
})
export class DoctorRegistrationComponent {
  doctor: any = {
    name: '',
    cpf: '',
    crm: '',
    email: '',
  };

  constructor(private doctorsService: DoctorsService) {}

  saveDoctor() {
    this.doctorsService.createDoctor(this.doctor).subscribe(
      (res) => {
        console.log('Doctor registration sent successfully');
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
