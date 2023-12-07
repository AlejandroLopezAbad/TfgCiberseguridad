import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { User } from "src/app/entities/user";
import { Login } from "src/app/entities/login";
import { environment } from "src/environments/environment";
import { Observable, catchError, throwError } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class UserService {
    constructor(private http: HttpClient) { }


    getAllUsers() {
        return this.http.get<User[]>(`${environment.server}/api/users/list`)
    }

    login(login: Login): Observable<HttpResponse<any>> {
        const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        return this.http.post<any>(`${environment.server}/api/users/login`, login, { observe: 'response', headers })
          .pipe(
            catchError((error: any) => {
              return throwError(error);
            })
          );
      }
}