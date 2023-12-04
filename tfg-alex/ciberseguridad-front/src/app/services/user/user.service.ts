import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { User } from "src/app/entities/user";
import { Login } from "src/app/entities/login";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root'
})
export class UserService {
    constructor(private http: HttpClient) { }


    getAllUsers() {
        return this.http.get<User[]>(`${environment.server}/api/users/list`)
    }

    login(login:Login) {
     return this.http.post(`${environment.server}/api/users/login`,login) 
    }
}