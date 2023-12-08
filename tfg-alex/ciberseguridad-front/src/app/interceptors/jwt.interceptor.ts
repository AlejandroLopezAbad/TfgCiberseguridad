

import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    
    
    constructor( private authService: AuthService) { }
  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
  

  
    const jwtToken = this.authService.rawtoken;
   
    if (jwtToken==undefined){
        return next.handle(request)
    }
    
    console.log(jwtToken)
    const authRequest = request.clone({
      setHeaders: {
        Authorization: `Bearer ${jwtToken}`,
      },
    });

 

   
    return next.handle(authRequest);
  }
}