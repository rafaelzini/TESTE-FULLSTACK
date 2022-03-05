import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'PIS'
})
export class PISPipe implements PipeTransform {

  transform(value: string, ...args: any[]): any {
    if (value.length === 11) {
      return value.replace(/(\d{3})(\d{5})(\d{2})(\d{1})/g, '\$1. \$2. \$3\-\$4');
    }
  }

}
