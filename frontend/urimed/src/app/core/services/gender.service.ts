import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class GenderService {
    private apiUrl = 'http://localhost:8080/genders';

    constructor(private http: HttpClient) { }

    getGenders(): Observable<any> {
        console.log(this.http.get(this.apiUrl));
        return this.http.get(this.apiUrl);
    }
}