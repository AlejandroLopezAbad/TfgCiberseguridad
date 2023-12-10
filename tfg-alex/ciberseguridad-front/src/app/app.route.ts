import { Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserTableComponent } from './components/user-table/user-table.component';
import { LoginComponent } from './components/login/login.component';
import { BankAccountTableComponent } from './components/bank-account-table/bank-account-table.component';
import { AuthGuard } from './services/authGuard.service';

const APP_ROUTES: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'user-table', component: UserTableComponent, canActivate: [AuthGuard] },
  { path: 'bank-account-table', component: BankAccountTableComponent,canActivate: [AuthGuard] },
  { path: '**', pathMatch: 'full', redirectTo: 'login' },
];

export const APP_ROUTING = RouterModule.forRoot(APP_ROUTES);