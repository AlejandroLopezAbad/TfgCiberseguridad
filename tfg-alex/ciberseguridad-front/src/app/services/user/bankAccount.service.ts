
import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { BankAccount } from "src/app/entities/bankAccount";
import { environment } from "src/environments/environment";




@Injectable({
    providedIn: 'root'
})
export class BankAccountService {
    constructor(private http: HttpClient) { }


    getAllBankAccount() {
        return this.http.get<BankAccount[]>(`${environment.server}/api/bankAccount/list`)
    }

   
}