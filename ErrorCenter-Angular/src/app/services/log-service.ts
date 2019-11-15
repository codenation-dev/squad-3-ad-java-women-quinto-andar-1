import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LogService {

  private url = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

   findLogById(id){

      return this.http.get(this.url+"/log/"+id);
  }
}
