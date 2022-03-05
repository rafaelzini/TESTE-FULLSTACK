import { Component, OnInit } from '@angular/core';

export interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}

export const ROUTES: RouteInfo[] = [
    { path: '/customer', title: 'Clientes', icon: 'fa fa-user-o', class: '' },

];

@Component({
    moduleId: module.id,
    selector: 'app-sidebar',
    templateUrl: 'sidebar.component.html',
    styleUrls: ['sidebar.component.scss'],
})

export class SidebarComponent implements OnInit {
    public menuItems: any[];
    ngOnInit() {
        this.menuItems = ROUTES.filter(menuItem => menuItem);
    }
}
