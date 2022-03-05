import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AngularEditorModule } from '@kolkov/angular-editor';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CPFPipeModule } from 'app/cpfpipe.module';
import { NgxMaskModule } from 'ngx-mask';
import { PaginatorModule } from 'primeng/paginator';
import { SelectButtonModule } from 'primeng/selectbutton';
import { TableModule } from 'primeng/table';
import { TooltipModule } from 'primeng/tooltip';
import { FilmeRoutingModule } from './filme.module.routing';
import { FilmeFormComponent } from './form/filme-form.component';
import { FilmeListComponent } from './list/filme-list.component';

@NgModule({
  declarations: [
    FilmeListComponent,
    FilmeFormComponent,

  ],

  exports: [
  ],

  imports: [
    CPFPipeModule,
    CommonModule,
    FormsModule,
    FilmeRoutingModule,
    NgbModule,
    NgxMaskModule,
    AngularEditorModule,
    TooltipModule,
    TableModule,
    PaginatorModule,
    SelectButtonModule,
  ],
  providers: [],

})
export class FilmeModule { }
