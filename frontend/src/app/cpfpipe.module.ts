import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CPFPipe } from './cpf.pipe';



@NgModule({
  imports: [
    CommonModule,
  ],
  declarations: [
    CPFPipe,
  ],
  exports: [
    CPFPipe,
  ],
})
export class CPFPipeModule { }
