import { Component, OnInit } from '@angular/core';

import { User } from 'src/app/entities/user';
import { UserService } from 'src/app/services/user.service';

@Component({
    selector: 'app-user-table',
    templateUrl: './user-table.component.html',
    styleUrls: ['./user-table.component.scss']
})
export class UserTableComponent implements OnInit {

    users!: User[];

    constructor(private userService: UserService) {}

    ngOnInit() {
        this.getUsers();
    }

    getUsers() {
        this.userService.getUsers().subscribe(result => {
            this.users = result;
        });
    }




}