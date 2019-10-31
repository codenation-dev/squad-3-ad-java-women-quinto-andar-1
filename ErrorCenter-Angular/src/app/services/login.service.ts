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
    let body = {
      "login": username,
      "password": password
    }
    return this.http.post<any>( this.url + '/api/auth/login', body).pipe(
      map(
        userData => {
          console.log("userData");
          console.log(userData);

         sessionStorage.setItem('username',username);
         let tokenStr= 'Bearer ' + userData.accessToken;
         sessionStorage.setItem('token', tokenStr);
         return userData;
        }
      )
 
     );
     
    //return this.http.post<any>(this.url + '/api/auth/login', body);
  }

  logOut() {
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('token');
  }

}
