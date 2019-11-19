import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Log } from '../model/log';
import { LogService } from '../services/log.service';

@Component({
  selector: 'app-log-view',
  templateUrl: './log-view.component.html',
  styleUrls: ['./log-view.component.css']
})
export class ErrorViewComponent implements OnInit {

  public ip;
  public data;
  public hora;
  public status;
  public titulo;
  public level;
  public eventos;
  public details;
  public token;
  public id = sessionStorage.getItem('logId');

  constructor(private router: Router, private logService: LogService) { }

  ngOnInit() {
    this.logService.getLogById(this.id)
    .subscribe(data => {
    console.log(data);
    this.getItems(data);
    //this.ip = ip;  // trocar depois
    //this.data = data.data;
    //this.hora = data.hora;
    //this.status = data.status;
    //this.titulo = data.titulo;
    //this.token = data.token;
    });
  }

  getItems(data){
    console.log(data);
    this.ip = data.origin;  // trocar depois
    this.data = data.dataLogged;
   // this.hora = data.hora;
    this.level = data.level;
    this.eventos = data.event;
    this.details = data.details;
    this.status = data.status;
    this.titulo = data.description;
    this.token = data.user.token;
  }

  getColor(level: string){
    if(level == "error") return "red";
    if(level == "warning") return "orange";
    if(level == "debug") return "blue";

  }
}
