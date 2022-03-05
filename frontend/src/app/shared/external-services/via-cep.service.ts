import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { INTERCEPTOR_SKIP_HEADER } from 'app/layouts/oauth/request-interceptor.service';
import { Observable } from 'rxjs';
import { retry } from 'rxjs/operators';

@Injectable()
export class ViaCepService {

    readonly URL_VIA_CEP = 'https://viacep.com.br/ws/'

    constructor(protected http: HttpClient) { }

    private generatorHeaders() {
        let headers = new HttpHeaders();
        headers = headers.append('Content-Type', 'application/json')
        return headers
    }

    getByZipCode(zipCode: string): Observable<ViaCep> {
        const headers = new HttpHeaders().set(INTERCEPTOR_SKIP_HEADER, 'true');
        return this.http.get<ViaCep>(`${this.URL_VIA_CEP}/${zipCode}/json/`, { headers })
            .pipe(retry(1));
    }

}

export class ViaCep {
    cep: string
    logradouro: string
    complemento: string
    bairro: string
    localidade: string
    uf: string
    ibge: string
    gia: string
    ddd: string
    siafi: string
}
