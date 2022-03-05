import { Routes } from '@angular/router';
import { OAuthGuard } from '../oauth/oauth.guard';

export const AdminLayoutRoutes: Routes = [
    {
        canActivate: [OAuthGuard],
        runGuardsAndResolvers: 'always'
    },
];
