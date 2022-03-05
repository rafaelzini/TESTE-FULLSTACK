import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';
import { INTERCEPTOR_SKIP_HEADER } from './request-interceptor.service';


@Injectable()
export class OAuthService {

  oauthTokenUrl = environment.backEndOauthTokenUrl;
  logged = false;

  constructor(
    private httpClient: HttpClient,
    protected http: HttpClient,
    private jwtHelperService: JwtHelperService,
    private router: Router,
  ) {
    this.loadingToken();
  }

  login(username?: string, password?: string): Promise<void> {
    let headers = new HttpHeaders();

    headers = headers.append('Content-type', 'application/x-www-form-urlencoded; charset=utf-8')
      .append('Authorization', 'Basic b2F1dGg6eWJaJDVoMzE=');

    const body = `username=${username}&password=${password}&grant_type=password&scope=read`;

    return this.httpClient.post(this.oauthTokenUrl, body, { headers })
      .toPromise()
      .then(resp => {

        const response: any = resp;
        this.storeToken(response.access_token, response.refresh_token);

        this.logged = this.isLogado();
      })
      .catch(error => {
        return Promise.reject(error);
      });
  }

  getNewAccessToken(): Promise<void> {

    const headers = new HttpHeaders(
      {
        'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
        Authorization: 'Basic b2F1dGg6eWJaJDVoMzE='
      }
    );

    const tokenInformation: string = localStorage.getItem('rtk');
    const body = `grant_type=refresh_token&refresh_token=${tokenInformation}`;

    return this.httpClient.post(this.oauthTokenUrl, body, { headers })
      .toPromise()
      .then(resp => {
        const response: any = resp;
        if (localStorage.getItem('token')) {
          this.storeToken(response.access_token, response.refresh_token);
        }
        return Promise.resolve(null);
      })
      .catch(error => {
        console.log(error)
        return Promise.reject(error);
      });
  }

  getOauthHeaders() {
    const headers = new HttpHeaders(
      {
        'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
        Authorization: 'Basic b2F1dGg6eWJaJDVoMzE='
      }
    );

    return headers;
  }

  public logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }

  public isExpired(): boolean {
    return this.jwtHelperService.isTokenExpired(localStorage.getItem('token'));
  }

  private storeToken(token: string, refreshToken: string) {
    localStorage.setItem('rtk', refreshToken);
    localStorage.setItem('token', token);
    localStorage.setItem('infoUsuario', JSON.stringify(this.jwtHelperService.decodeToken(token)));
  }

  private loadingToken() {
    const token = localStorage.getItem('token');
    if (token) {
      localStorage.setItem('token', token);
    }
  }

  isLogado(): boolean {
    return localStorage.getItem('token') !== null;
  }

  saveEstablishemnt(establishment): Observable<any> {
    const headers = new HttpHeaders().set(INTERCEPTOR_SKIP_HEADER, 'true');
    return this.http.post<any>("environment.backEndOauthUrl" + '/establishment', establishment, { headers })
  }

  newPassword(token, password): Observable<any> {
    const headers = new HttpHeaders().set(INTERCEPTOR_SKIP_HEADER, 'true');
    const endpoint = "/account/new-password/";
    return this.http.patch<any>("environment.backEndOauthUrl" + endpoint + token + '?password=' + password, { headers });
  }

  recoverPassword(username): Observable<any> {
    const headers = new HttpHeaders().set(INTERCEPTOR_SKIP_HEADER, 'true');
    const endpoint = "/account/recover-password/";
    return this.http.patch<any>("environment.backEndOauthUrl" + endpoint + username, { headers });
  }

}
