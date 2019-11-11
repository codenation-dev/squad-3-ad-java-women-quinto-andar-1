import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap, catchError } from 'rxjs/operators';
import { Log } from '../model/log';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class LogService {

  private logUrl = 'https://central-de-erros.herokuapp.com';
	//private logUrl = '/api';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  header = {
    headers: new HttpHeaders()
      .set('Authorization',  sessionStorage.getItem('token'))
  }

  constructor(private http: HttpClient) { }

  /**GET logs */
  getLogs(){
    console.log("GET DOS LOGS");
    console.log(this.header);
    return this.http.get(this.logUrl + "/api/log", this.header);
  }

  addLog(Log){
    return this.http.post(this.logUrl + "log", Log);
  }

  /**Get log by Id */
  getLogById(id){
    return this.http.get(this.logUrl + "/api/log/" + id);
  }

  changeStatus(Log) {
    return this.http.put(this.logUrl + "/"+ Log.id, Log);
  // talvez tenha que mudar esse metodo. Pelo que vi o changeStatus dá um save. Será que não conflita com um log já existente?
  }


  /**Post user */
  postLogs(body: any){
    return this.http.post(this.logUrl + "/api/auth/cad", body);
  }

  findLogByEnvironmentAndOrderBy(environment: String, orderBy: String){
    return this.http.get<Log[]>(this.logUrl + "/api/{environment}/{orderBy}")}

  findLogByEnvironmentAndSearchBy(environment: String, searchBy: String){
    return this.http.get<Log[]>(this.logUrl + "/api/{environment}/{orderBy}")}

  findLogByEnvironmentAndOrderByAndSearchBy(environment: String, orderBy: String, searchBy: String){
    return this.http.get<Log[]>(this.logUrl + "/api/{environment}/{orderBy}/{searchBy}")}

}
