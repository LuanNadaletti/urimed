import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { MenuItem } from '../models/menu-item';

@Injectable({
  providedIn: 'root'
})
export class MenuService {
  getMenuItems(): Observable<MenuItem[]> {
    const menuItems: MenuItem[] = [
      { label: 'Página Inicial', route: '/home', icon: 'home' },
      { label: 'Pacientes', route: '/patients', icon: 'person' },
      { label: 'Médicos', route: '/doctors', icon: 'medical_services' }
    ];

    return of(menuItems);
  }
}