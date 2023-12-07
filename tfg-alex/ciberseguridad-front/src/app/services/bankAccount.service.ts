
import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { BankAccount } from "src/app/entities/bankAccount";
import { environment } from "src/environments/environment";
import { AuthService } from "./auth.service";
import { map } from "rxjs";




@Injectable({
    providedIn: 'root'
})
export class BankAccountService {
    constructor(private http: HttpClient, private authService: AuthService) { }


    getAllBankAccount() {
        return this.http.get<BankAccount[]>(`${environment.server}/api/bankAccount/list`)
    }

    getMyBankAccount(id:String){
        return this.http.get<BankAccount[]>(`${environment.server}/api/bankAccount/cuentasociada/${id}`)
    }


    getBankAccount(){
        if (this.authService.token?.roles.includes("USER")) {
    
          return this.getMyBankAccount(this.authService.token.sub!!);
    
        } else if (this.authService.token?.roles.includes("ADMIN")) {
          return this.getAllBankAccount()
        } else {
          throw new Error("")
        }
      }

   
}