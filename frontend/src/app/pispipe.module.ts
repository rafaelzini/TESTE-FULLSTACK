import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PISPipe } from './pis.pipe';



@NgModule({
  imports: [
    CommonModule,
  ],
  declarations: [
    PISPipe,
  ],
  exports: [
    PISPipe,
  ],
})
export class PISPipeModule { }
