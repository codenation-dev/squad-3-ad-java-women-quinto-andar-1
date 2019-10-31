import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  //Informacoes do usuario e token
  userName = "Usuário";
  userToken = "jsjsjjsjsjsjsjjsjsjsjs";

  formLogin;
  
  constructor(private fb: FormBuilder, private router: Router, private loginService: LoginService) { }

  ngOnInit() {
    this.formLogin = this.fb.group({
      email: []
    });
    this.getUsername();
  }

  getUsername(){
    console.log("Usuario logado: ");
   this.userName = sessionStorage.getItem('username')
   console.log(this.userName);
  }

  gotoCadastroClientes() {
    this.router.navigate(['/user-register']);
   }

  logout(){
    this.loginService.logOut();
    this.router.navigate(['/login']);
  }
}
