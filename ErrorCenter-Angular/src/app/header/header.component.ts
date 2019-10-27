import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

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
  
  constructor(private fb: FormBuilder, private router: Router) { }

  ngOnInit() {
    this.formLogin = this.fb.group({
      email: ['']
    });
  }

  gotoCadastroClientes() {
    this.router.navigate(['/user-register']);
   }
}
