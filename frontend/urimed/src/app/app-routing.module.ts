import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DoctorRegistrationComponent } from './core/pages/doctor-registration/doctor-registration.component';
import { LoginComponent } from './core/pages/login/login.component';
import { AuthGuard } from './core/guards/auth.guard';
import { PatientRegistrationComponent } from './core/pages/patient-registration/patient-registration.component';
import { LayoutComponent } from './core/template/layout/layout.component';
import { ScheduleAppointmentComponent } from './core/pages/schedule-appointment/schedule-appointment.component';
import { DoctorGuard } from './core/guards/doctor.guard';
import { DoctorScheduleComponent } from './core/pages/doctor-schedule/doctor-schedule.component';
import { DoctorAppointmentComponent } from './core/pages/doctor-appointment/doctor-appointment.component';
import { ConsultationComponent } from './core/pages/consultation/consultation.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: '',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'doctors',
        component: DoctorRegistrationComponent,
        canActivate: [AuthGuard],
      },
      {
        path: 'patients',
        component: PatientRegistrationComponent,
        canActivate: [AuthGuard],
      },
      {
        path: 'appointments',
        component: ScheduleAppointmentComponent,
        canActivate: [AuthGuard],
      },
      {
        path: 'doctor-schedule',
        component: DoctorScheduleComponent,
        canActivate: [AuthGuard],
      },
      {
        path: 'doctor-appointments',
        component: DoctorAppointmentComponent,
        canActivate: [AuthGuard],
      },
      {
        path: 'consultation',
        component: ConsultationComponent,
        canActivate: [AuthGuard],
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
