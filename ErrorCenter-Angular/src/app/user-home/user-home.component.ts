import { Component, OnInit, ElementRef } from '@angular/core';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit {
  getCadastro;
  nameCliente;

  constructor(private el: ElementRef) { }

  ngOnInit() {
    console.log(this.el.nativeElement);

    this.getCadastro = JSON.parse(localStorage.getItem('cadastro'));
    this.nameCliente = this.getCadastro['nome'];
    console.log(this.nameCliente);
  }


}
