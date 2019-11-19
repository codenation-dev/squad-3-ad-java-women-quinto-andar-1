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
  // talvez tenha que mudar esse metodo. Pelo que vi o changeStatus dá um save. Será que não conflita com um log já existente?
  }



  findLogByEnvironmentAndOrderBy(environment: String, orderBy: String){
    return this.http.get<Log[]>(this.logUrl + "/api/{environment}/{orderBy}")}

  findLogByEnvironmentAndSearchBy(environment: String, searchBy: String){
    return this.http.get<Log[]>(this.logUrl + "/api/{environment}/{orderBy}")}

  findLogByEnvironmentAndOrderByAndSearchBy(environment: String, orderBy: String, searchBy: String){
    return this.http.get<Log[]>(this.logUrl + "/api/{environment}/{orderBy}/{searchBy}")}

}
