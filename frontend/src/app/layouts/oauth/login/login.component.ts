import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OAuthService } from 'app/layouts/oauth/oauth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {

  accountUsername: string = '';
  accountPassword: string = '';
  accountName: string = '';
  accountCnpjCpf: string = '';
  invalidUser: boolean = false;
  disableInterface: boolean = false;
  registerCard: boolean = false;

  loginCard: boolean = false;
  forgotPasswordCard: boolean = false;

  waitingRegister: boolean = false;

  passwordErrorMessage: string = '';
  passwordError = true;

  requestNewPassword: boolean = false;

  constructor(
    private oauthService: OAuthService,
    private route: Router,
  ) { }

  ngOnInit(): void {
    this.loginCard = true;
  }

  login() {
    this.requestNewPassword = false;
    this.invalidUser = false;
    this.disableInterface = true;
    this.oauthService.login(this.accountUsername, this.accountPassword).then(() => {
      this.disableInterface = false;
      this.route.navigate(['/home']);
    })
      .catch(error => {
        this.disableInterface = false;
        this.invalidUser = true;
        console.log(error);
      })
  }

  recoverPassword() {
    this.disableInterface = true;
    this.oauthService.recoverPassword(this.accountUsername).subscribe(resp => {
      this.requestNewPassword = true;
      this.disableInterface = false;
    }, error => {
      console.log(error);
      this.passwordErrorMessage = error.error.message;
      this.passwordError = true;
      this.disableInterface = false;
    })
  }

  register() {
    
  }

  lostPasswordCard() {
    this.requestNewPassword = false;
    this.loginCard = false;
    this.waitingRegister = false;
    this.registerCard = false;
    this.forgotPasswordCard = true;
  }

  backToLoginCard() {
    this.requestNewPassword = false;
    this.forgotPasswordCard = false;
    this.waitingRegister = false;
    this.registerCard = false;
    this.loginCard = true;
  }

  showRegisterCard() {
    this.requestNewPassword = false;
    this.loginCard = false;
    this.forgotPasswordCard = false;
    this.waitingRegister = false;
    this.registerCard = true;
  }

}
