import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap, catchError } from 'rxjs/operators';
import { VariableEnvironmentService } from './variable-environment.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl = this.variableEnvironment.url;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient, private variableEnvironment : VariableEnvironmentService) { }

  /**GET users */
  getUsers(){
    return this.http.get(this.userUrl + "/api/users");
  }
  /**Get user by Id */
  getUserById(id){
    return this.http.get(this.userUrl + "/api/user/" + id);
  }

  /**Post user */
  postUsers(body){
    return this.http.post(this.userUrl + "/api/auth/cad", body);
  }
}
