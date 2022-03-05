import { HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Filme } from 'app/pages/filme/shared/filme';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';
import { BaseFilter } from '../models/base-filter.model';
import { TesteResponse } from '../models/teste-response.model';
import { BaseService } from './base.service';

@Injectable()
export class FilmeService extends BaseService<Filme> {


URL = "/filme";

getFilmes(filter: BaseFilter): Observable < TesteResponse < Filme >> {
    const httpParams = new HttpParams()
        .append('search', filter.search)
        .append('page', filter.page.toString())
        .append('size', filter.size.toString());
    return this.http.get<TesteResponse<Filme>>(environment.backEndUrl + `/filme`, { params: httpParams });
};

}
