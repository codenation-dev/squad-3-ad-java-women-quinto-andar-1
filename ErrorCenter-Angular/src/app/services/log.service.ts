import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap, catchError } from 'rxjs/operators';
import { Log } from '../model/log';
import { VariableEnvironmentService } from './variable-environment.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class LogService {

  private logUrl = this.variableEnvironment.url;
	//private logUrl = '/api';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  header = {
    headers: new HttpHeaders()
      .set('Authorization',  sessionStorage.getItem('token'))
  }

  constructor(private http: HttpClient, private variableEnvironment : VariableEnvironmentService) { }

  /**GET logs */
  getLogs(){
    console.log("GET DOS LOGS");
    return this.http.get(this.logUrl + "/api/logs", this.header);
  }

  // addLog(Log){
  //   return this.http.post(this.logUrl + "logs", Log);
  // }

  /**Get log by Id */
  getLogById(id){
    return this.http.get(this.logUrl + "/api/logs/id/" + id, this.header);
  }

  changeStatus(id, body) {
    return this.http.put(this.logUrl + "/api/logs/status/"+ id, body, this.header);
  }

  findLogByEnvironment(environment: String){
    return this.http.get(this.logUrl + "/api/logs/" + environment, this.header);
  }

  findLogByEnvironmentAndOrderByLevel(environment: String, orderBy: String){
    return this.http.get(this.logUrl + "/api/logs/envOrdLev/" + environment, this.header);
  }

  findLogByEnvironmentAndOrderByEvent(environment: String, orderBy: String){
    return this.http.get(this.logUrl + "/api/logs/envOrdEve/" + environment, this.header);
  }

  findLogByEnvironmentAndSearchByAndOrderByLevel(environment: String, searchBy: String){
    return this.http.get(this.logUrl + "/api/logs/envOrdLev/" + environment + "/" + searchBy, this.header);
  }

  findLogByEnvironmentAndSearchByAndOrderByEvent(environment: String, searchBy: String){
    return this.http.get(this.logUrl + "/api/logs/envOrdEve/" + environment + "/" + searchBy, this.header);
  }

  findLogByEnvironmentAndSearchBy(environment: String, searchBy: String){
    return this.http.get(this.logUrl + "/api/logs/" + environment + "/" + searchBy, this.header);
  }
  
}
