import { Component, OnInit } from '@angular/core';
import { AppointmentService } from '../../services/appointment.service';
import { Router } from '@angular/router';
import { SharedService } from '../../services/shared.service';

interface Appointment {
  time: string;
  patient: string;
}

@Component({
  selector: 'app-doctor-appointment',
  templateUrl: './doctor-appointment.component.html',
  styleUrls: ['./doctor-appointment.component.css'],
})
export class DoctorAppointmentComponent implements OnInit {
  appointments: Appointment[] = [];
  displayedColumns: string[] = ['time', 'patient', 'actions'];

  constructor(
    private appointmentService: AppointmentService,
    private router: Router,
    private sharedService: SharedService
  ) {}

  ngOnInit(): void {
    this.appointmentService
    .getAppointmentsForToday(localStorage.getItem('token'))
    .subscribe((data) => {
      this.appointments = data;
    });
  }

  startConsultation(appointment: Appointment): void {
    this.sharedService.setData(appointment);
    this.router.navigate(['/consultation']);
  }
}
