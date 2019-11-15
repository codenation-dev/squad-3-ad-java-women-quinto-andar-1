import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LogService } from '../services/log-service';
import { Log } from '../model/log';

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
  public token;
  public id = sessionStorage.getItem('logId');

  constructor(private router: Router, private logService: LogService) { }

  ngOnInit() {
    this.logService.findLogById(this.id)
    .subscribe(data => {
    console.log(data);
    //this.ip = data.ip;  // trocar depois
    //this.data = data.data;
    //this.hora = data.hora;
    //this.status = data.status;
    //this.titulo = data.titulo;
    //this.token = data.token;
    });
  }
}
