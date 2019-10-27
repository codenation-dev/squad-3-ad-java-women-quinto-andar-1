import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { SelectionModel } from '@angular/cdk/collections';

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

/** Constants used to fill up our data base. */
const EVENTOS: string[] = [
  '1000', '300', '100', '11', '2'
];
const LEVELS: string[] = [
  'error', 'warning', 'debug', 'warning', 'error'
];

const ELEMENT_DATA: UserData[] = [
  {id: '1', level: 'error', log: "Descrição do log", evento: '1000'},
  {id: '2', level: 'warning', log: "Descrição do log", evento: '400'},
  {id: '3', level: 'debug', log: "Descrição do log", evento: '374'},
  {id: '4', level: 'debug', log: "Descrição do log", evento: '100'},
  {id: '5', level: 'error', log: "Descrição do log", evento: '22'},
  {id: '6', level: 'error', log: "Descrição do log", evento: '11'},
];

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {
  // ----- Select Forms -----
  selectedAmb = 'producao';

  // ----- Tabela -----
  displayedColumns: string[] = ['select', 'id', 'level', 'log', 'evento'];
  dataSource: MatTableDataSource<UserData>;
  selection = new SelectionModel<UserData>(true, []);

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private router: Router) {

    // Para mockup aqui --------------------------------------------------------------------
    // Create 100 users
    //const users = Array.from({ length: 100 }, (_, k) => createNewUser(k + 1));

    // Assign the data to the data source for the table to render

    this.dataSource = new MatTableDataSource<UserData>(ELEMENT_DATA);
    // Fim do mockup-------------------------------------------------------------------------
  }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  gotoCadastroClientes() {
    this.router.navigate(['/user-register']);
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

/** Builds and returns a new User. */
function createNewUser(id: number): UserData {
  const level = LEVELS[Math.round(Math.random() * (LEVELS.length - 1))] + ' ' +
    LEVELS[Math.round(Math.random() * (LEVELS.length - 1))].charAt(0) + '.';

  return {
    id: id.toString(),
    level: level,
    log: Math.round(Math.random() * 100).toString(),
    evento: EVENTOS[Math.round(Math.random() * (EVENTOS.length - 1))]
  };

  
}
