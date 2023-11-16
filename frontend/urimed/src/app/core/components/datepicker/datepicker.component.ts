import { Component, forwardRef } from '@angular/core';
import {
  ControlValueAccessor,
  FormControl,
  NG_VALUE_ACCESSOR,
} from '@angular/forms';

@Component({
  selector: 'app-datepicker',
  templateUrl: './datepicker.component.html',
  styleUrls: ['./datepicker.component.css'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => DatepickerComponent),
      multi: true,
    },
  ],
})
export class DatepickerComponent {
  dateControl = new FormControl();

  writeValue(value: any): void {
    this.dateControl.setValue(value);
  }

  registerOnChange(fn: any): void {
    this.dateControl.valueChanges.subscribe(fn);
  }

  registerOnTouched(fn: any): void {
    this.dateControl.valueChanges.subscribe(fn);
  }
}
