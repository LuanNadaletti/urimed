import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'; // Importe o HttpClientModule

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DoctorRegistrationComponent } from './core/components/pages/doctor-registration/doctor-registration.component';
import { DoctorsService } from './core/services/doctor.service';
import { SidebarComponent } from './core/components/template/sidebar/sidebar.component';
import { MenuService } from './core/services/menu.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { TopbarComponent } from './core/components/template/topbar/topbar.component';

@NgModule({
  declarations: [
    AppComponent,
    DoctorRegistrationComponent,
    SidebarComponent,
    TopbarComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatIconModule
  ],
  providers: [
    DoctorsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
