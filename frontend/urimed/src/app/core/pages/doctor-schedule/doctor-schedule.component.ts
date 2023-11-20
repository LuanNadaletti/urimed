import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DoctorAvailability } from '../../models/doctor-availability.interface';
import { Doctor } from '../../models/doctor.interface';
import { DoctorAvailabilityService } from '../../services/doctor-availability.service';
import { PersonService } from '../../services/person.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-doctor-schedule',
  templateUrl: './doctor-schedule.component.html',
  styleUrls: ['./doctor-schedule.component.css'],
})
export class DoctorScheduleComponent implements OnInit {
  scheduleForm: FormGroup;
  dayNames = ['Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'];
  doctor: Doctor;

  constructor(
    private formBuilder: FormBuilder,
    private doctorAvailabilityService: DoctorAvailabilityService,
    private personService: PersonService,
    private toastrService: ToastrService
  ) {}

  ngOnInit(): void {
    this.scheduleForm = this.formBuilder.group(
      this.dayNames.reduce((acc: any, dayName: string) => {
        acc[dayName] = this.createDayFormGroup();
        return acc;
      }, {})
    );

    const token = localStorage.getItem('token');
    if (token) {
      this.personService.getDoctorByUsername(token).subscribe((doctor) => {
        this.doctor = doctor;
      });
    }

    console.log(this.doctor);
  }

  createDayFormGroup(): FormGroup {
    return this.formBuilder.group({
      startTime: [''],
      endTime: [''],
    });
  }

  initTimeSlot() {
    return this.formBuilder.group({
      startTime: [''],
      endTime: [''],
    });
  }

  saveSchedule(): void {
    let availabilities: DoctorAvailability[] = this.dayNames
      .map((dayName) => {
        let dayGroup = this.scheduleForm.get(dayName)?.value;

        if (!dayGroup?.startTime || !dayGroup?.endTime) {
          return null;
        }

        let availability: DoctorAvailability = {
          doctor: this.doctor,
          dayOfWeek: dayName,
          startTime: dayGroup.startTime,
          endTime: dayGroup.endTime,
        };

        return availability;
      })
      .filter(
        (availability): availability is DoctorAvailability =>
          availability !== null
      );

      this.doctorAvailabilityService.createAll(availabilities).subscribe(      (res) => {
        this.toastrService.success('Horários cadastrados com sucesso', 'Sucesso');
        this.scheduleForm.reset();
      },
      (err) => {
        this.toastrService.error('Não foi possível cadastrar os horários', 'Erro');
      });
  }
}
