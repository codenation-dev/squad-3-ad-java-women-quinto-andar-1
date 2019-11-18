import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class VariableEnvironmentService {

  public url = 'https://central-de-erros.herokuapp.com';
  //public url = 'http://localhost:8080';


  constructor() { }
}
