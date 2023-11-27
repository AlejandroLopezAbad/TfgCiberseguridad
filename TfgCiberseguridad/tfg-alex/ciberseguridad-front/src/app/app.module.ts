import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TableModule } from 'primeng/table';
import { UserTableComponent } from './components/user-table/user-table.component';
import { APP_ROUTING } from './app.route';
import { HttpClientModule } from '@angular/common/http';
import { TabMenuModule } from 'primeng/tabmenu';
import { InputTextModule } from 'primeng/inputtext';
import { NavComponent } from './components/nav/nav.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DataViewModule } from 'primeng/dataview';
import { BankAccountTableComponent } from './components/bank-account-table/bank-account-table.component';


@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    UserTableComponent,
    LoginComponent,
    BankAccountTableComponent
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TableModule,
    APP_ROUTING,
    HttpClientModule,
    TabMenuModule,
    InputTextModule,
    FormsModule,
    ButtonModule,
    DataViewModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
