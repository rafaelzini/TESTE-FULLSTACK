import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './oauth/login/login.component';
import { NewPasswordComponent } from './oauth/new-password/new-password.component';

export const rotas: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'nova-senha', component: NewPasswordComponent },
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(rotas)
  ],
  providers: [
    RouterModule
  ]
})
export class CoreRoutingModule { }
