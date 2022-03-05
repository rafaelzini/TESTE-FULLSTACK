import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { PagesModule } from 'app/pages/pages.module';

import { AdminLayoutRoutes } from './admin-layout.routing';

@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    HttpClientModule,
    FormsModule,
    NgbModule,
    PagesModule,
  ],
  providers: []
})

export class AdminLayoutModule { }
