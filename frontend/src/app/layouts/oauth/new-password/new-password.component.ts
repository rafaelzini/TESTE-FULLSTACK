import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OAuthService } from '../oauth.service';

@Component({
  selector: 'app-new-password',
  templateUrl: './new-password.component.html',
  styleUrls: ['./new-password.component.scss']
})
export class NewPasswordComponent implements OnInit {

  token: string;
  password: string;
  passwordConfirm: string;
  disableInterface: boolean = false;

  passwordErrorMessage: string = '';
  passwordError = true;

  defineNewPassword: boolean = false;

  constructor(
    private router: Router,
    private oauthService: OAuthService,
    private activatedRoute: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.defineNewPassword = false;
    this.activatedRoute.queryParams.subscribe(params => {
      this.token = params['token'];
    });
  }

  newPassword() {
    this.passwordError = false;

    if ((this.password, this.passwordConfirm) === undefined || null || (this.password.length === 0)) {
      this.passwordErrorMessage = "Por favor, preencha os campos da senha.";
      this.passwordError = true;
      return console.log('Não executa função, campos nao validados');

    } if (this.password === this.passwordConfirm) {

      return this.oauthService.newPassword(this.token, this.passwordConfirm).subscribe(
        resp => {
          this.defineNewPassword = true;
        },
        error => {
          console.log(error);
          this.passwordErrorMessage = error.error.message;
          this.passwordError = true;
        }
      );

    } else {
      this.passwordErrorMessage = "Senhas não coincidem."
      this.passwordError = true;
      return console.log('Não executa o cadastro, senhas não coincidem.');
    };

  }

}
