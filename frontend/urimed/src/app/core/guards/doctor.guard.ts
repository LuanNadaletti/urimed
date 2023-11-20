import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  Router,
} from '@angular/router';
import { Observable, catchError, map, of } from 'rxjs';
import { PersonService } from '../services/person.service';

@Injectable({
  providedIn: 'root',
})
export class DoctorGuard {
  constructor(private router: Router, private personService: PersonService) {}

  canActivate():
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
        const token = localStorage.getItem('token');

        if (!token) {
            this.router.navigate(['/login']);
            return of(false);
        }

        return this.personService.isDoctor(token).pipe(
            catchError(() => {
                this.router.navigate(['/login']);
                return of(false);
            })
        );
    }
}
