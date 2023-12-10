import { Injectable } from "@angular/core";
import { HttpClient, } from '@angular/common/http';
import { User } from "src/app/entities/user";

import { environment } from "src/environments/environment";
import { AuthService } from "./auth.service";
import { map } from "rxjs";
import { UserWithToken } from "../entities/userWithToken";



@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient, private authService: AuthService) { }


  getAllUsers() {
    return this.http.get<User[]>(`${environment.server}/api/users/list`)
  }

//Ines
// getUserById(id: string) {
   //return this.http.get<User>(`${environment.server}/api/users/${id}`)
 // }

  getmeInfo(){
   return this.http.get<User>(`${environment.server}/api/users/me`)
 }

  getUsers() {
    if (this.authService.token?.roles.includes("USER")) {

      return this.getmeInfo().pipe(
        map(response => { return [response] })
      );

    } else if (this.authService.token?.roles.includes("ADMIN")) {
      return this.getAllUsers()
    } else {
      throw new Error("")
    }
  }

}
