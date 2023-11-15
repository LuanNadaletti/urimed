import { Component, Input } from '@angular/core';
import { MenuItem } from '../../models/menu-item.interface';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {
  @Input() items: MenuItem[] = [];
}
