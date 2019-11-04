import { MatDialog } from '@angular/material';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ModalNotRegisteredComponent } from '../modal-not-registered/modal-not-registered.component';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formLogin;
  theEvent;
  key;
  regex;
  keys;
  getCadastro;
  message;
  isFailedLogin = false;

  constructor(private fb: FormBuilder,
    private router: Router,
    public dialog: MatDialog,
    private loginService: LoginService) { }

  ngOnInit() {
    this.formLogin = this.fb.group({
      login: [],
      password: []
    });
    this.isUserLoggedIn();
  }

  isUserLoggedIn(){
    if(this.loginService.isLoggedIn()){
      this.router.navigate(['/home'])
    }
  }

  onlynumber(evt) {
    this.theEvent = evt || window.event;
    this.key = this.theEvent.keyCode || this.theEvent.which;
    this.key = String.fromCharCode(this.key);
    this.regex = /^[0-9.]+$/;
    if (!this.regex.test(this.key)) {
      this.theEvent.returnValue = false;
      if (this.theEvent.preventDefault) {
        this.theEvent.preventDefault();
      }
    }
  }

  login() {
    //this.getCadastro = JSON.parse(localStorage.getItem('cadastro'));
    //console.log("this.getCadastro");
    //console.log(this.getCadastro);
    //const emailPersistido = this.getCadastro['email'];
    //console.log("emailPersistido");
    //console.log(typeof(emailPersistido));
    const emailDigitado = this.formLogin.get('login').value;
    const passwordDigitado = this.formLogin.get('password').value;

    console.log("emailDigitado");
    console.log(emailDigitado);
    console.log(typeof(emailDigitado));
    console.log(passwordDigitado);
    console.log(typeof(passwordDigitado));

    this.loginService.login(emailDigitado, passwordDigitado)
      .subscribe(response => {
        console.log(response);
        this.router.navigate(['/home'])
      },
      Error => {
        this.isFailedLogin = true;
        console.log("Error");
        console.log(Error);
      });

    //if (emailPersistido == emailDigitado) {
    //  console.log("São iguais");
    //  this.router.navigate(['/user-home']);
    //} else {
    //  this.openDialog();
    //}
  }

  openDialog() {
    const dialogRef = this.dialog.open(ModalNotRegisteredComponent, {
      height: '350px'
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}


