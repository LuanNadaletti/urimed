import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { SelectOneMenuComponent } from './select-one-menu.component';

describe('SelectOneMenuComponent', () => {
  let component: SelectOneMenuComponent;
  let fixture: ComponentFixture<SelectOneMenuComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SelectOneMenuComponent],
      imports: [
        MatFormFieldModule,
        MatSelectModule,
        MatAutocompleteModule,
        HttpClientTestingModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
      ],
    });
    fixture = TestBed.createComponent(SelectOneMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
