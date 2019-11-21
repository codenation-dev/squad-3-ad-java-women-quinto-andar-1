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
    this.getItems(data);
    });
  }

  getItems(data){
    this.ip = data.origin;
    this.data = data.dataLogged;
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
