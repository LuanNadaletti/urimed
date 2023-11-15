import { TestBed, waitForAsync } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { ToastrModule } from 'ngx-toastr';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatExpansionModule } from '@angular/material/expansion';

import { TopbarComponent } from './core/template/topbar/topbar.component';
import { SidebarComponent } from './core/template/sidebar/sidebar.component';
import { MenuComponent } from './core/components/menu/menu.component';

describe('AppComponent', () => {
  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [AppComponent, TopbarComponent, SidebarComponent, MenuComponent],
      imports: [
        ToastrModule.forRoot(),
        HttpClientModule,
        RouterModule.forRoot([]),
        MatIconModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatExpansionModule,
      ],
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });
});
