import { Component, OnInit } from '@angular/core';
import { MenuService } from '../../services/menu.service';
import { MenuItem } from '../../models/menu-item.interface';
import { PersonService } from '../../services/person.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
})
export class SidebarComponent implements OnInit {
  menuItems: MenuItem[] = [];

  constructor(private menuService: MenuService, private personService: PersonService) {}

  ngOnInit() {
    let username: string = localStorage.getItem('token') as string;
  
    this.personService.getRoleByUsername(username).subscribe((role) => {
      if (role && role.name) {
        let userType = role.name;
        this.menuService.getMenuItems(userType).subscribe((items) => {
          this.menuItems = items;
        });
      }
    });
  }
}
