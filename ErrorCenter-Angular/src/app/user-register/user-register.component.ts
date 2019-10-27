import { MatDialog } from '@angular/material';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { debounceTime } from 'rxjs/operators';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  formCadastro;
  valoresForm: Object;
  conversao;

  constructor(private fb: FormBuilder, public dialog: MatDialog, private router: Router, private userService: UserService) { }

  ngOnInit() {
    //localStorage.clear();
    this.formCadastro = this.fb.group({
      //nome: [''],
      login: [],
      password: []
    });

    this.formCadastro.valueChanges.pipe(
      debounceTime(1000))
      .subscribe(res => {
        console.log(res);
        this.valoresForm = res;
      });

      //this.userService.getUsers().subscribe(users => {
      //  console.log(users);
      //});
  }

  cadastro() {
    console.log("this.formCadastro.value");
    console.log(this.formCadastro.value);
    this.conversao = JSON.stringify(this.valoresForm);
    console.log("this.conversao");
    console.log(this.conversao);
    //localStorage.setItem('cadastro', this.conversao);


    this.userService.postUsers(this.formCadastro.value).subscribe(users => console.log(users));
    
    this.verificaCadastro();
  }

  verificaCadastro() {
    //setTimeout(() => {
      //console.log(localStorage.getItem('cadastro'));
      //if (localStorage.getItem('cadastro')) {
        // TODO REDIRECIIONAR PARA PAGINA DE CADASTRO CONCLUIDO
        this.router.navigate(['/succesful-register']);
      //} else {
      //  return false;
      //}
    //}, 200);
  }

}
