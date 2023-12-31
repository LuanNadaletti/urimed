import { Component } from '@angular/core';
import { LayoutService } from './core/services/layout.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {

  constructor(private layoutService: LayoutService, private router: Router, private activatedRoute: ActivatedRoute) { }
}
