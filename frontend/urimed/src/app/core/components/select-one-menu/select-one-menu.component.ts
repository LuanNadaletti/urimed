import { Component, Input, forwardRef } from '@angular/core';
import { FormControl, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'app-select-one-menu',
  templateUrl: './select-one-menu.component.html',
  styleUrls: ['./select-one-menu.component.css'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => SelectOneMenuComponent),
      multi: true,
    },
  ],
})
export class SelectOneMenuComponent {
  @Input() label: string;
  @Input() items: any[];
  @Input() displayField: string;
  control = new FormControl();

  writeValue(obj: any): void {
    this.control.setValue(obj);
  }

  registerOnChange(fn: any): void {
    this.control.valueChanges.subscribe(fn);
  }

  registerOnTouched(fn: any): void {
    this.control.valueChanges.subscribe(fn);
  }
}
