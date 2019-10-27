import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap, catchError } from 'rxjs/operators';
import { User } from '../User';
import { log } from 'util';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl = 'http://localhost:8080';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  /**GET users */
  getUsers(){
    return this.http.get(this.userUrl + "/api/users");
  }
  /**Get user by Id */

  /**Post user */
  postUsers(body){
    return this.http.post(this.userUrl + "/api/user", body);
  }
}
