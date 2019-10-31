import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private url = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  login(username, password){
    return this.http.post<any>( this.url + '/authenticate', {username,password}).pipe(
      map(
        userData => {
         sessionStorage.setItem('username',username);
         let tokenStr= 'Bearer '+userData.token;
         sessionStorage.setItem('token', tokenStr);
         return userData;
        }
      )
 
     );
  }
}
