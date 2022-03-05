import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OAuthGuard } from 'app/layouts/oauth/oauth.guard';

export const routes: Routes = [

    {
        path: 'filme',
        loadChildren: () => import('./filme/filme.module')
            .then(module => module.FilmeModule),
    },


];
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PagesRoutingModule { }
