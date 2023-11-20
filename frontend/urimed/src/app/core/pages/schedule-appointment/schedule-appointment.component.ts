import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Doctor } from '../../models/doctor.interface';
import { SpecialtyService } from '../../services/specialty.service';
import { Specialty } from '../../models/specialty.interface';
import { Appointment } from '../../models/appointment.interface';
import { ScheduleRequest } from '../../models/schedule-request.interface';
import { PersonService } from '../../services/person.service';
import { ToastrService } from 'ngx-toastr';
import { Patient } from '../../models/patient.interface';
import { tap } from 'rxjs';
import { AppointmentService } from '../../services/appointment.service';

@Component({
  selector: 'app-schedule-appointment',
  templateUrl: './schedule-appointment.component.html', // Refer to the HTML file
  styleUrls: ['./schedule-appointment.component.css'],
})
export class ScheduleAppointmentComponent implements OnInit {
  specialties: Specialty[];

  appointmentForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private specialtyService: SpecialtyService,
    private personService: PersonService,
    private toastrService: ToastrService,
    private appointmentService: AppointmentService,
  ) {}

  ngOnInit(): void {
    this.specialtyService.getEspecialties().subscribe((specialties) => {
      this.specialties = specialties.map((specialty: Specialty) => {
        return {
          id: specialty.id,
          name: specialty.name,
        };
      });
    });

    this.appointmentForm = this.createAppointmentForm();
  }

  saveAppointment() {
    this.createScheduleRequest().then(scheduleRequest => {
      console.log(scheduleRequest);
      this.appointmentService.scheduleAppointment(scheduleRequest).subscribe(response => {
        this.toastrService.success('Consulta agendada com sucesso!');
        this.appointmentForm = this.createAppointmentForm();
      }, error => {
        console.error(error);
      });
    });
  }

  createAppointmentForm(): FormGroup {
    return this.fb.group({
      date: ['', Validators.required],
      time: ['', Validators.required],
      specialty: ['', Validators.required],
    });
  }

  async createScheduleRequest(): Promise<ScheduleRequest> {
    const dateValue = this.appointmentForm.get('date')?.value;
    const timeValue = this.appointmentForm.get('time')?.value;
    const specialtyValue = this.appointmentForm.get('specialty')?.value;
  
    const patient = await this.personService.getLoggedPatient().toPromise();

    if (!patient) {
      this.toastrService.error('Você precisa estar logado para agendar uma consulta');
      throw new Error('User not logged in');
    }
  
    return {
      specialty: {
        id: specialtyValue,
      },
      date: dateValue,
      dayOfWeek: this.getDayOfWeek(dateValue),
      startTime: timeValue,
      endTime: timeValue,
      patient: patient
    };
  }

  getDayOfWeek(date: Date): string {
    const dateObject = new Date(date);
    const daysOfWeek = ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'];
    return daysOfWeek[dateObject.getDay()];
  }
}
