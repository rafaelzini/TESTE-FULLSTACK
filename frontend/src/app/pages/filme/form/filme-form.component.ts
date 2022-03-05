import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FilmeService } from 'app/shared/services/filme.service';
import { TextEditorConfig } from 'app/shared/utils/text-editor-config';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Filme } from '../shared/filme';

@Component({
  selector: 'app-filme-form',
  templateUrl: './filme-form.component.html',
  styleUrls: ['./filme-form.component.scss']

})
export class FilmeFormComponent implements OnInit {

  filme: Filme;
  pageTitle: string;
  btnName: string;
  stateOptions: any[];
  lograVerify: boolean;
  compleVerify: boolean;
  districtVerify: boolean;
  cityVerify: boolean;
  stateVerify: boolean;
  modalUpdateRef: BsModalRef;
  @ViewChild('modalUpdate') modalUpdate;
  filmeUpdate: Filme;
  loadingUpdate: Boolean = false;
  config = TextEditorConfig.globalConfig;

  constructor(
    private filmeService: FilmeService,
    private router: Router,
    private route: ActivatedRoute,
    private modalService: BsModalService,
  ) { }

  ngOnInit(): void {
    this.filme = this.newFilme();

    const filmeId = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id != null) {
        this.districtVerify = true;
        this.lograVerify = true;
        this.btnName = 'Atualizar';
        this.pageTitle = 'Editar';
        this.filmeService.getById(id).subscribe(resp => {
          this.filme = resp;
        })
      } else {
        this.btnName = 'Salvar';
        this.pageTitle = 'Cadastrar';
      }
    })

  }

  private newFilme(): Filme {
    const filme = new Filme();
    return filme;
  }

  filmeModalUpdate(filme) {
    this.filmeUpdate = filme;
    this.modalUpdateRef = this.modalService.show(this.modalUpdate, { class: 'modal-sm' });
  }

  actionBtnFilme() {
    this.loadingUpdate = true;
    this.filmeService.save(this.filme).subscribe(respSave => {
      this.loadingUpdate = false;
      this.modalUpdateRef.hide();
      this.router.navigate([`/filme/list`]);
    },
      error => {
        console.log(error)
      });
  };
  cancelUpdate() {
    this.modalUpdateRef.hide();
  }

}
