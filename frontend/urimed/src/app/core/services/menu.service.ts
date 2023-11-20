import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { MenuItem } from '../models/menu-item.interface';

@Injectable({
  providedIn: 'root',
})
export class MenuService {
  adminMenuItems: MenuItem[] = [
    { label: 'Página Inicial', destination: '/', icon: 'home' },
    { label: 'Consultas', destination: '/doctor-appointments', icon: 'list' },
    { label: 'Agendamento', destination: '/appointments', icon: 'list' },
    {
      label: 'Cadastros',
      destination: '',
      children: [
        { label: 'Pacientes', destination: '/patients', icon: 'person' },
        { label: 'Médicos', destination: '/doctors', icon: 'medical_services' },
        { label: 'Horários', destination: '/doctor-schedule', icon: 'schedule' },
      ],
      icon: 'create',
    },
  ];

  patientMenuItems: MenuItem[] = [
    { label: 'Página Inicial', destination: '/', icon: 'home' },
    { label: 'Agendamento', destination: '/appointments', icon: 'list' },
  ];

  doctorMenuItems: MenuItem[] = [
    { label: 'Página Inicial', destination: '/', icon: 'home' },
    { label: 'Consultas', destination: '/doctor-appointments', icon: 'list' },
    { label: 'Horários', destination: '/doctor-schedule', icon: 'schedule' },
  ];

  getMenuItems(userType: string): Observable<MenuItem[]> {
    switch (userType) {
      case 'admin':
        return of(this.adminMenuItems);
      case 'patient':
        return of(this.patientMenuItems);
      case 'doctor':
        return of(this.doctorMenuItems);
      default:
        return of([]);
    }
  }
}
