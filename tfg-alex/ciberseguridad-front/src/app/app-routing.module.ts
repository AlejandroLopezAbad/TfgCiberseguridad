import { NgModule } from '@angular/core';
import { RouterModule, Routes, withHashLocation } from '@angular/router';

const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash:true})], //TODO si quiero que no puedan acceder a las otras paginas , les obligo que pasen por el login 
  exports: [RouterModule]
})
export class AppRoutingModule { }
