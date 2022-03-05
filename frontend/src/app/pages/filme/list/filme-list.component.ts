import { Component, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { BaseFilter } from 'app/shared/models/base-filter.model';
import { TesteResponse } from 'app/shared/models/teste-response.model';
import { FilmeService } from 'app/shared/services/filme.service';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { LazyLoadEvent } from 'primeng/api';
import { Filme } from '../shared/filme';

@Component({
  selector: 'app-filme-list',
  templateUrl: './filme-list.component.html',
  styleUrls: ['./filme-list.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class FilmeListComponent implements OnInit {

  filmes: Filme[] = [];
  virtualDatabase: Filme[];
  totalRecords: number;
  paginationListFilme: TesteResponse<Filme> = new TesteResponse<Filme>();
  filmeFilter = new BaseFilter();
  searchValue: string = '';
  filme: Filme;

  itemsPerPage: number;
  totalElements: number;
  page: number;
  pageSize: number = 10;

  modalActiveOrNotRef: BsModalRef;
  @ViewChild('modalActiveOrNot') modalActiveOrNot;
  filmeActiveOrNot: Filme;
  modalDeleteRef: BsModalRef;
  @ViewChild('modalDelete') modalDelete;
  loading: Boolean = true;
  filmeDelete: Filme;
  loadingDeletar: boolean = false;
  modalLoadingPdfRef: BsModalRef;
  @ViewChild('modalLoadingPdf') modalLoadingPdf;
  loadingPdf: Boolean = true;

  constructor(
    private router: Router,
    private modalService: BsModalService,
    private filmeSevice: FilmeService,

  ) { }

  ngOnInit(): void {
    this.filmeFilter.search = '';
    this.filmeFilter.page = 0;
    this.filmeFilter.size = 10;
    this.page = 1;
    this.getFilmes();

  }

  filmeModalActiveOrNot(filme: Filme) {
    this.filmeActiveOrNot = filme;
    this.modalActiveOrNotRef = this.modalService.show(this.modalActiveOrNot, { class: 'modal-sm' });
  }

  getFilmes(event: LazyLoadEvent = undefined) {
    if (event) {
      this.page = event.first;
      this.filmeFilter.size = event.rows;
      if (event.first === 0) {
        this.filmeFilter.page = event.first;
      } else {
        this.filmeFilter.page = (Math.ceil(event.first / this.filmeFilter.size));
      }
      this.filmeSevice.getFilmes(this.filmeFilter).subscribe(
        res => {
          this.paginationListFilme = res;
          this.filmes = res.content;
          this.virtualDatabase = res.content;
          this.itemsPerPage = res.pageSize;
          this.filmeFilter.size = event.rows;
          this.totalElements = res.totalElements;
          this.loading = false;
          this.totalRecords = res.totalElements;
        },
        error => {
          console.log(error);
          this.loading = false;
        }
      )

    } else {
      this.loading = true;
      this.page = 1;

      this.filmeSevice.getFilmes(this.filmeFilter).subscribe(
        resp => {
          this.paginationListFilme = resp;
          this.filmes = resp.content.slice(0, this.pageSize);
          this.virtualDatabase = resp.content
          this.itemsPerPage = resp.pageSize;
          this.totalElements = resp.totalElements;
          this.loading = false;
          this.totalRecords = resp.totalElements;
        },
        error => {
          console.log(error);
          this.loading = false;
        }
      )
    }
  }

  private searchParameters() {
    this.filmeFilter.search = this.searchValue;
  }

  search() {
    this.loading = true;
    this.searchParameters();
    this.filmeFilter.page = 0;
    this.page = 1;

    this.filmeSevice.getFilmes(this.filmeFilter).subscribe(
      resp => {
        this.paginationListFilme = resp;
        this.filmes = resp.content;
        this.itemsPerPage = resp.pageSize;
        this.totalElements = resp.totalElements;
        this.loading = false;

      },
      error => {
        console.log(error);
        this.loading = false;
      });

  }

  pageChanged(event: any): void {
    this.loading = true;
    let numberPage: number = event;
    numberPage--;
    this.filmeFilter.page = numberPage

    this.filmeSevice.getFilmes(this.filmeFilter).subscribe(
      resp => {
        this.filmes = resp.content;
        this.itemsPerPage = resp.pageSize;
        this.loading = false;
      },
      error => {
        console.log(error);
        this.loading = false;
      }
    )
    this.page = event;

  }

  filmeModalDelete(customer) {
    this.filmeDelete = customer;
    this.modalDeleteRef = this.modalService.show(this.modalDelete, { class: 'modal-sm' });
  }

  confirmDelete() {
    this.loadingDeletar = true;
    this.filmeSevice.delete(this.filmeDelete.id).subscribe(
      respDelete => {
        this.loadingDeletar = false;
        this.getFilmes();
        this.modalDeleteRef.hide();
      },
      error => {
        this.loadingDeletar = false;
        console.log(error);

      }
    );
  }

  confirmActiveOrNot(id) {
    this.filmeSevice.activeDesactive(id).subscribe(
      respActiveOrNot => {
        this.getFilmes();
        this.modalActiveOrNotRef.hide();
      },
      error => {
        this.loadingDeletar = false;
        console.log(error);
      }
    );
  }

  cancelDelete() {
    this.modalDeleteRef.hide();
  }
  cancelActiveOrNot() {
    this.modalActiveOrNotRef.hide();
  }

  editFilme(id) {
    this.router.navigate([`/filme/form/${id}`]);
  }
}