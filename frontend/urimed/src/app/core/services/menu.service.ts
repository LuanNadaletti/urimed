import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { MenuItem } from '../models/menu-item.interface';

@Injectable({
  providedIn: 'root'
})
export class MenuService {
  getMenuItems(): Observable<MenuItem[]> {
    const menuItems: MenuItem[] = [
      { label: 'Página Inicial', destination: '/', icon: 'home' },
      { label: 'Cadastros', destination: '', children: [
        { label: 'Pacientes', destination: '/patients', icon: 'person' },
        { label: 'Médicos', destination: '/doctors', icon: 'medical_services' },
      ], icon: 'create' },
    ];

    return of(menuItems);
  }
}