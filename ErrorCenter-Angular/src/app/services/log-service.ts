import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LogService {

  private url = 'https://central-de-erros.herokuapp.com';

  constructor(private http: HttpClient) { }

   findLogById(id){

      return this.http.get(this.url+"/log/"+id);
  }
}
