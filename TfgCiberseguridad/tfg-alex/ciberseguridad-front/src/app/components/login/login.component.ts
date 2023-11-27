import { Component, OnInit } from "@angular/core";
import { Login } from "src/app/entities/login";

import { Router } from '@angular/router';
import { UserService } from "src/app/services/user/user.service";

@Component({
    selector: 'app-home',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    login!:Login;
    showPass: boolean=false;
    showMessage: boolean = false;

    constructor(private router:Router, private userService: UserService) {
        this.login = new Login();
    }

    ngOnInit(): void {

    }
    
    showPassword() {
        
        this.showPass = !this.showPass;
        if(!this.showPass) {
        }
    }

    log() {
        if(this.login.email != null && this.login.password != null) {
           
            this.userService.login(this.login).subscribe(resul => {
                console.log(resul);
                this.router.navigate(['/user-table']);
            });
        } else {
            this.showMessage = true;
        }
    }
}