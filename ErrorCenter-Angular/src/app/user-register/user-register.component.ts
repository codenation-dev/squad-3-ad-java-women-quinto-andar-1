import { MatDialog, MatSnackBar } from '@angular/material';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { debounceTime } from 'rxjs/operators';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';
import { SuccessfulRegisterComponent } from '../successful-register/successful-register.component';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  formCadastro;
  valoresForm: Object;
  conversao;
  durationInSeconds = 5;

  constructor(private fb: FormBuilder,
    public dialog: MatDialog,
    private router: Router,
    private userService: UserService,
    private loginService: LoginService,
    private _snackBar: MatSnackBar) { }

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

    this.isUserLoggedIn();
    //this.userService.getUsers().subscribe(users => {
    //  console.log(users);
    //});
  }

  isUserLoggedIn(){
    if(this.loginService.isLoggedIn()){
      this.router.navigate(['/home'])
    }
  }

  cadastro() {
    console.log("this.formCadastro.value");
    console.log(this.formCadastro.value);
    this.conversao = JSON.stringify(this.valoresForm);
    console.log("this.conversao");
    console.log(this.conversao);
    //localStorage.setItem('cadastro', this.conversao);

    // let userInfo = {
    //   email: this.formCadastro.value.email,
    //   login: this.formCadastro.value.login,
    //   password: this.formCadastro.value.password,
    //creationDate: "2019-12-10", 
    //lastActivity: "2019-12-10"
    // }
    // console.log(userInfo);

    let user = new User();
    user.email = this.formCadastro.value.email;
    user.login = this.formCadastro.value.login;
    user.password= this.formCadastro.value.password;
    //user.creationDate= "2019-12-10";
    //user.lastActivity= "2019-12-10";

    console.log("user");
    console.log(user);
    //console.log(JSON.stringify(user));

    this.userService.postUsers(user).subscribe(users => {
      console.log(users);
      //this.verificaCadastro();
      //this.router.navigate(['/succesful-register']);
      this.router.navigate(['/login']);
      this.openSnackBar();
    },
      Error => {
        console.log(Error);
        alert("Erro ao cadastrar!");

      }
    );
  }

  openSnackBar() {
    this._snackBar.openFromComponent(SuccessfulRegisterComponent, {
      duration: this.durationInSeconds * 1000,
    });
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
