import { MatDialog } from '@angular/material';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ModalNotRegisteredComponent } from '../modal-not-registered/modal-not-registered.component';

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

  constructor(private fb: FormBuilder,
    private router: Router,
    public dialog: MatDialog) { }

  ngOnInit() {
    this.formLogin = this.fb.group({
      email: [''],
      senha: []
    });
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
    this.getCadastro = JSON.parse(localStorage.getItem('cadastro'));
    console.log("this.getCadastro");
    console.log(this.getCadastro);
    const emailPersistido = this.getCadastro['email'];
    console.log("emailPersistido");
    console.log(typeof(emailPersistido));
    const emailDigitado = this.formLogin.get('email').value;
    console.log("emailDigitado");
    console.log(typeof(emailDigitado));
    if (emailPersistido == emailDigitado) {
      console.log("São iguais");
      this.router.navigate(['/user-home']);
    } else {
      this.openDialog();
    }
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


