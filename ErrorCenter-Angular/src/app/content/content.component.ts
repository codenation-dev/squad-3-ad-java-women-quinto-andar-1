import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { SelectionModel } from '@angular/cdk/collections';
import { LogService } from '../services/log.service';

export interface UserData {
  id: string;
  level: string;
  log: string;
  evento: string;
}

export interface ResponseBody{
  dataHora: Date;
  ipOrigem: string,
  titulo: string;
  detalhes: string;
  eventos: number;
  coletadoToken: string;
}

const DELETED: string = 'deletado';
const ARCHIVED: string = 'arquivado';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {
  // ----- Select Forms -----
  selectedEnvironment = 'producao';
  selectedSearchBy;
  selectedOrderBy;
  searchBy = "";

  // ----- Tabela -----
  displayedColumns: string[] = [/*'select', 'id', */'level', 'log', 'evento', 'visualize', 'archive', 'delete'];
  dataSource: MatTableDataSource<UserData> = new MatTableDataSource<UserData>();;
  selection = new SelectionModel<UserData>(true, []);

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  // ----- status -----
  deleted = DELETED;
  archived = ARCHIVED;


  constructor(private router: Router, private logService: LogService) {
  }

  ngOnInit() {
    this.search();
  }

  search(){
    if(this.selectedOrderBy != null && this.searchBy != null){
      if(this.selectedOrderBy == 'evento'){
        this.logService.findLogByEnvironmentAndSearchByAndOrderByEvent(this.selectedEnvironment, this.searchBy)
        .subscribe(
          response=>{
            let res = response;
            this.fillTable(res);
          }
        );
        return;
      }
      if(this.selectedOrderBy == 'level'){
        this.logService.findLogByEnvironmentAndSearchByAndOrderByLevel(this.selectedEnvironment, this.searchBy)
        .subscribe(
          response=>{
            let res = response;
            this.fillTable(res);
          }
        );
        return;
      }
    }

    if(this.selectedOrderBy == null && this.searchBy != null){
        this.logService.findLogByEnvironmentAndSearchBy(this.selectedEnvironment, this.searchBy)
        .subscribe(
          response=>{
            let res = response;
            this.fillTable(res);
          }
        );
        return;
    }

    if(this.selectedOrderBy != null && this.searchBy == null){
      if(this.selectedOrderBy == 'evento'){
        this.logService.findLogByEnvironmentAndOrderByEvent(this.selectedEnvironment, this.selectedOrderBy)
        .subscribe(
          response=>{
            let res = response;
            this.fillTable(res);
          }
        );
        return;
      }

      if(this.selectedOrderBy == 'level'){
        this.logService.findLogByEnvironmentAndOrderByLevel(this.selectedEnvironment, this.selectedOrderBy)
        .subscribe(
          response=>{
            let res = response;
            this.fillTable(res);
          }
        );
        return;
      }
    }

    this.logService.findLogByEnvironment(this.selectedEnvironment)
    .subscribe(
      response=>{
        let res = response;
        this.fillTable(res);
      }
    );
    return;
  }

  fillTable(res){
    var logFiltered = res.filter(item => this.isActive(item));

    this.dataSource = new MatTableDataSource<UserData>(logFiltered);

    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  isActive(item){
      if(item.status == 'ativo') return true;
      return false;
  }

  expandLog(id){
    sessionStorage.setItem('logId', id);
    this.router.navigate(['/logview']);
  }

  changeLogStatusById(id, status: string){
    sessionStorage.setItem('logId', id);
    this.logService.getLogById(id).subscribe(response => {
      this.changeLogStatus(response, status);
    });
  }

  changeLogStatus(logById, status){
    logById.status = status;

    this.logService.changeStatus(logById.id, logById).subscribe();
    this.ngOnInit();
  }

  getColor(level: string){
    if(level == "error") return "red";
    if(level == "warning") return "orange";
    if(level == "debug") return "blue";

  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
        this.selection.clear() :
        this.dataSource.data.forEach(row => this.selection.select(row));
  }

  /** The label for the checkbox on the passed row */
  checkboxLabel(row?: UserData): string {
    if (!row) {
      return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.id + 1}`;
  }
}

  

