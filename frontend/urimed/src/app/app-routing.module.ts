import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DoctorRegistrationComponent } from './core/pages/doctor-registration/doctor-registration.component';
import { LoginComponent } from './core/pages/login/login.component';
import { AuthGuard } from './core/services/authguard.service';
import { PatientRegistrationComponent } from './core/pages/patient-registration/patient-registration.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'doctors', component: DoctorRegistrationComponent, canActivate: [AuthGuard] },
  { path: 'patients', component: PatientRegistrationComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
