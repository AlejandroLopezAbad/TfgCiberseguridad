import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Login } from "src/app/entities/login";
import { environment } from "src/environments/environment";
import { Observable, catchError, map, switchMap, throwError } from "rxjs";
import { JwtPayload, jwtDecode } from "jwt-decode";
import { UserWithToken } from "../entities/userWithToken";
import { User } from "../entities/user";

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    token?: JwtPayload & {roles:string};
    constructor(private http: HttpClient) { }


    login(login: Login): Observable<HttpResponse<UserWithToken>> {
        const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        return this.http.post<UserWithToken>(`${environment.server}/api/users/login`, login, { observe: 'response', headers })
            .pipe(
                map((response) => {
                    if (response.status == 200 && response.body != undefined) {
                        this.token = jwtDecode(response.body.token)
                       
                    }
                    return response;
                })
            );
    }



}