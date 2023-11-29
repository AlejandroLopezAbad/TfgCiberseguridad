import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { User } from "src/app/entities/user";
import { Login } from "src/app/entities/login";

@Injectable({
    providedIn: 'root'
})
export class UserService {
    constructor(private http: HttpClient) { }


    getAllUsers() {
        return this.http.get<User[]>('http://localhost:8081/api/users/list')
    }

    login(login:Login) {
        return this.http.post('http://localhost:8081/api/users/login',login);
    }
}