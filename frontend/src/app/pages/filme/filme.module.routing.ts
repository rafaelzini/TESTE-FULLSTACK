import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OAuthGuard } from 'app/layouts/oauth/oauth.guard';
import { FilmeFormComponent as FilmeFormComponent } from './form/filme-form.component';
import { FilmeListComponent as FilmeListComponent } from './list/filme-list.component';

export const routes: Routes = [
    { path: '', redirectTo: 'list' },
    { path: 'list', component: FilmeListComponent, canActivate: [OAuthGuard] },
    { path: 'form', component: FilmeFormComponent, canActivate: [OAuthGuard] },
    { path: 'form/:id', component: FilmeFormComponent, canActivate: [OAuthGuard] },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class FilmeRoutingModule { }
