import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { User } from 'src/app/entities/user';
import { UserService } from 'src/app/services/user/user.service';

@Component({
    selector: 'app-user-table',
    templateUrl: './user-table.component.html',
    styleUrls: ['./user-table.component.scss']
})
export class UserTableComponent implements OnInit {

    users!:User[];

    constructor(private userService:UserService) {
    
         this.getAllUsers();
    }

    ngOnInit() {
        
    }

    getAllUsers() {
        this.userService.getAllUsers().subscribe(result => {
            this.users = result;
        });
    }
}