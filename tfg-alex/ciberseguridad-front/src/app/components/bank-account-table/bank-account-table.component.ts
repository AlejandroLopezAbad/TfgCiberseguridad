import { Component } from '@angular/core';
import { BankAccount } from 'src/app/entities/bankAccount';

import { BankAccountService } from 'src/app/services/user/bankAccount.service';

@Component({
  selector: 'app-bank-account-table',
  templateUrl: './bank-account-table.component.html',
  styleUrls: ['./bank-account-table.component.scss']
})
export class BankAccountTableComponent {

  bankAccounts!:BankAccount[];

  constructor(private bankAccountService:BankAccountService) {
  
       this.getAllBankAccount();
  }

  ngOnInit() {
      
  }

  getAllBankAccount() {
      this.bankAccountService.getAllBankAccount().subscribe(result => {
          this.bankAccounts = result;
      });
  }

}


