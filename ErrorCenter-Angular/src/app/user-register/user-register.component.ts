import { MatDialog } from '@angular/material';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { debounceTime } from 'rxjs/operators';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';

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
      email: [],
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

    let userInfo = {
      email: this.formCadastro.value.email,
      login: this.formCadastro.value.login,
      password: this.formCadastro.value.password,
      creationDate: "2019-12-10", 
      lastActivity: "2019-12-10"
    }
    console.log(userInfo);

    let user = new User();
    user.email = this.formCadastro.value.email;
    user.login = this.formCadastro.value.login;
    user.password= this.formCadastro.value.password;
    user.creationDate= "2019-12-10";
    user.lastActivity= "2019-12-10";

    console.log("user");
    console.log(user);
    console.log(JSON.stringify(user));

    this.userService.postUsers(user).subscribe(users => console.log(users));
    
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
