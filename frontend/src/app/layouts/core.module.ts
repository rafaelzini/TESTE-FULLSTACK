import { CommonModule } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { JwtHelperService, JwtModule } from '@auth0/angular-jwt';
import { ViaCepService } from 'app/shared/external-services/via-cep.service';

import { BaseService } from 'app/shared/services/base.service';

import { FilmeService } from 'app/shared/services/filme.service';
import { CoreRoutingModule } from './core-routing.routes';
import { LoginComponent } from './oauth/login/login.component';
import { OAuthGuard } from './oauth/oauth.guard';
import { OAuthService } from './oauth/oauth.service';
import { RequestInterceptorService } from './oauth/request-interceptor.service';

import { NgxMaskModule } from 'ngx-mask';
import { NewPasswordComponent } from './oauth/new-password/new-password.component';

export function tokenGetter(): string {
  const resp: any = localStorage.getItem('token');
  return resp.access_token;

}

export const COMPONENTS = [
  LoginComponent
];

@NgModule({
  declarations: [
    COMPONENTS,
    NewPasswordComponent,

  ],
  imports: [
    CommonModule,
    FormsModule,
    CoreRoutingModule,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter,
      }
    }),
    FormsModule,
    NgxMaskModule,
  ],
  providers: [
    JwtHelperService,
    OAuthGuard,
    OAuthService,
    BaseService,

    ViaCepService,
    FilmeService,

    {
      provide: HTTP_INTERCEPTORS,
      useClass: RequestInterceptorService,
      multi: true
    }
  ],
  exports: [
    CoreRoutingModule,
    COMPONENTS
  ]
})
export class CoreModule { }
