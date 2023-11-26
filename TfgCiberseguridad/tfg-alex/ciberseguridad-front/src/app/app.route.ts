import { Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserTableComponent } from './components/user-table/user-table.component';
import { LoginComponent } from './components/login/login.component';

const APP_ROUTES: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'user-table', component: UserTableComponent },
  { path: '**', pathMatch: 'full', redirectTo: 'login' },
];

export const APP_ROUTING = RouterModule.forRoot(APP_ROUTES);