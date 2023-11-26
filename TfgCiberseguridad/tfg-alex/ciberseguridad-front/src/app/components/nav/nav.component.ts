import { Component, OnInit } from "@angular/core";
import { MenuItem } from "primeng/api";

@Component({
    selector: 'app-nav',
    templateUrl: './nav.component.html',
    styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {

    items!: MenuItem[];
    checked:boolean = false;
    activeItem: MenuItem | undefined;

    ngOnInit(): void {
        this.items = [
            { label: 'User', routerLink: '/user-table'},
        ];

        this.activeItem = this.items[0];
    }
    
}