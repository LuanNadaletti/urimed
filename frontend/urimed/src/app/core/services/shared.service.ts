// shared.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
  private dataSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);
  public dataObservable: Observable<any> = this.dataSubject.asObservable();

  setData(data: any): void {
    this.dataSubject.next(data);
  }
}