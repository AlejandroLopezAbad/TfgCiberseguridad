import { Component } from '@angular/core';
import { BankAccount } from 'src/app/entities/bankAccount';
import { AuthService } from 'src/app/services/auth.service';
import { BankAccountService } from 'src/app/services/bankAccount.service';



@Component({
  selector: 'app-bank-account-table',
  templateUrl: './bank-account-table.component.html',
  styleUrls: ['./bank-account-table.component.scss']
})
export class BankAccountTableComponent {

  bankAccounts!:BankAccount[];

  constructor(private bankAccountService:BankAccountService) {
  
  
  }

  ngOnInit() {
    this.getAllBankAccount();
  }

  getAllBankAccount() {
      this.bankAccountService.getBankAccount().subscribe(result => {
          this.bankAccounts = result;
      });
  }

 

}


