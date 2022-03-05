import { CommonModule, registerLocaleData } from '@angular/common';
import ptBr from '@angular/common/locales/pt';
import { DEFAULT_CURRENCY_CODE, LOCALE_ID, NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CPFPipeModule } from 'app/cpfpipe.module';
import { PISPipeModule } from 'app/pispipe.module';
import { NgxCurrencyModule } from 'ngx-currency';
import { PaginatorModule } from 'primeng/paginator';
import { TableModule } from 'primeng/table';
import { TooltipModule } from 'primeng/tooltip';
import { FilmeModule } from './filme/filme.module';

import { PagesRoutingModule } from './pages.module.routing';

registerLocaleData(ptBr);
@NgModule({
  declarations: [],
  imports: [
    NgxCurrencyModule,
    CPFPipeModule,
    PISPipeModule,
    CommonModule,
    NgbModule,
    PagesRoutingModule,
    FilmeModule,
    TooltipModule,
    TableModule,
    PaginatorModule,
  ],
  providers: [
    { provide: DEFAULT_CURRENCY_CODE, useValue: 'BRL' },
    { provide: LOCALE_ID, useValue: 'pt' },
  ],
})
export class PagesModule { }
