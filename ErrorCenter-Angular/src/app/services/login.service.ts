import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { VariableEnvironmentService } from './variable-environment.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private url = this.variableEnvironment.url;

  header = {
    headers: new HttpHeaders()
      .set('Authorization',  sessionStorage.getItem('token'))
  }

  constructor(private http: HttpClient, private variableEnvironment : VariableEnvironmentService) { }

  login(username, password){
    let body = {
      "login": username,
      "password": password
    }
    return this.http.post<any>( this.url + '/api/auth/login', body).pipe(
      map(
        userData => {
         sessionStorage.setItem('username', userData.user.login);
         sessionStorage.setItem('userToken', userData.user.token);

         let tokenStr= 'Bearer ' + userData.jwtAuthenticationResponse.accessToken;
         sessionStorage.setItem('token', tokenStr);
         return userData;
        }
      )
    );
  }

  logOut() {
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('userToken');
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('logId');
  }

  isLoggedIn(){
    if(sessionStorage.getItem('username')){
      return true;
    }
    return false;
  }

}
