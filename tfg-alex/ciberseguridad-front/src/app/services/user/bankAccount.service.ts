
import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { BankAccount } from "src/app/entities/bankAccount";




@Injectable({
    providedIn: 'root'
})
export class BankAccountService {
    constructor(private http: HttpClient) { }


    getAllBankAccount() {
        return this.http.get<BankAccount[]>('http://localhost:8081/api/bankAccount/list')
    }

   
}