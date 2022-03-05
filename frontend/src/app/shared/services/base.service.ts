import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable()
export class BaseService<T> {

  public BASE_URL = environment.backEndUrl
  URL: string

  constructor(protected http: HttpClient) { }

  generatorHeaders() {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`,
    });
    return headers;
  }

  getAll() {
    return this.http.get<T[]>(`${this.BASE_URL}${this.URL}`, ).pipe(
      retry(3), // retry a failed request up to 3 times
      catchError(this.handleError) // then handle the error
    );
  }

  getById(id: string): Observable<T> {
    return this.http.get<T>(`${this.BASE_URL}${this.URL}/${id}`, )
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  save(object: T): Observable<T> {

    console.log("OIII ",object)
    return this.http
      .post<T>(
        this.BASE_URL + this.URL,
        JSON.stringify(object),
        
      )
      .pipe(retry(1), catchError(this.handleError));
  }

  delete(id: string) {
    return this.http
      .delete<T>(`${this.BASE_URL}${this.URL}/${id}`, )
      .pipe(retry(1), catchError(this.handleError));
  }

  update(object: T, id: string): Observable<T> {
    return this.http
      .post<T>(`${this.BASE_URL}${this.URL}/${id}`, JSON.stringify(object), )
      .pipe(retry(1), catchError(this.handleError));
  }

  activeDesactive(id: string) {
    return this.http
      .put<T>(`${this.BASE_URL}${this.URL}/${id}`, null)
      .pipe(retry(1), catchError(this.handleError));
  }

  handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: ${error.error}`
      );
    }
    // Return an observable with a user-facing error message.
    return throwError('Something bad happened; please try again later.');
  }

}
