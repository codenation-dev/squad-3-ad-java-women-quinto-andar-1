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

    this.getCadastro = JSON.parse(sessionStorage.getItem('username'));
    this.nameCliente = this.getCadastro['username'];
    console.log(this.nameCliente);
  }


}
