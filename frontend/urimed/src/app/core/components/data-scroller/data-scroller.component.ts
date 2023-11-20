import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-data-scroller',
  templateUrl: './data-scroller.component.html',
  styleUrls: ['./data-scroller.component.css']
})
export class DataScrollerComponent {
  @Input() items: any[];
}
