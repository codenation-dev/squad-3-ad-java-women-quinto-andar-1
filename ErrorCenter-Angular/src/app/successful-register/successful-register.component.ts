import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-successful-register',
  templateUrl: './successful-register.component.html',
  styleUrls: ['./successful-register.component.css']
})
export class SuccessfulRegisterComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
    this.navegarParaLoginAutomaticamente();
  }

  navegarParaLoginAutomaticamente() {
    setTimeout(() => {
      this.router.navigate(['/login']);
    }, 100000);
  }

  navegarParaLogin() {
      this.router.navigate(['/login']);
  }
  
}
