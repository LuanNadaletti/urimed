import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DoctorRegistrationComponent } from './core/pages/doctor-registration/doctor-registration.component';
import { DoctorService } from './core/services/doctor.service';
import { SidebarComponent } from './core/template/sidebar/sidebar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { TopbarComponent } from './core/template/topbar/topbar.component';
import { ToastrModule } from 'ngx-toastr';
import { NgxMaskModule } from 'ngx-mask';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { RouterModule } from '@angular/router';
import { DatepickerComponent } from './core/components/datepicker/datepicker.component';
import { MatSelectModule } from '@angular/material/select';
import { SelectOneMenuComponent } from './core/components/select-one-menu/select-one-menu.component';
import { SpecialtyService } from './core/services/specialty.service';
import { LoginComponent } from './core/pages/login/login.component';
import { AuthService } from './core/services/auth.service';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MenuComponent } from './core/components/menu/menu.component';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatListModule } from '@angular/material/list';
import { PatientRegistrationComponent } from './core/pages/patient-registration/patient-registration.component';
import { PatientService } from './core/services/patient.service';
import { LayoutService } from './core/services/layout.service';
import { LayoutComponent } from './core/template/layout/layout.component';
import { ScheduleAppointmentComponent } from './core/pages/schedule-appointment/schedule-appointment.component';
import { DataScrollerComponent } from './core/components/data-scroller/data-scroller.component';
import { PersonService } from './core/services/person.service';
import { DoctorScheduleComponent } from './core/pages/doctor-schedule/doctor-schedule.component';
import { DoctorAvailabilityService } from './core/services/doctor-availability.service';
import { DoctorAppointmentComponent } from './core/pages/doctor-appointment/doctor-appointment.component';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { ConsultationComponent } from './core/pages/consultation/consultation.component';
import { AppointmentService } from './core/services/appointment.service';
import { SharedService } from './core/services/shared.service';
import { ConsultationService } from './core/services/consultation.service';

@NgModule({
  declarations: [
    AppComponent,
    DoctorRegistrationComponent,
    SidebarComponent,
    TopbarComponent,
    DatepickerComponent,
    SelectOneMenuComponent,
    LoginComponent,
    MenuComponent,
    PatientRegistrationComponent,
    LayoutComponent,
    ScheduleAppointmentComponent,
    DataScrollerComponent,
    DoctorScheduleComponent,
    DoctorAppointmentComponent,
    ConsultationComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatIconModule,
    ReactiveFormsModule,
    ToastrModule.forRoot({
      timeOut: 5000,
      closeButton: true,
      progressBar: true,
      preventDuplicates: true,
    }),
    NgxMaskModule.forRoot(),
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatInputModule,
    RouterModule.forRoot([]),
    MatSelectModule,
    MatAutocompleteModule,
    MatExpansionModule,
    MatListModule,
    MatCardModule,
    MatTableModule,
  ],
  exports: [
    SidebarComponent,
    TopbarComponent,
    DatepickerComponent,
    SelectOneMenuComponent,
    MenuComponent,
  ],
  providers: [
    DoctorService,
    PatientService,
    SpecialtyService,
    AuthService,
    LayoutService,
    PersonService,
    DoctorAvailabilityService,
    AppointmentService,
    SharedService,
    ConsultationService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
