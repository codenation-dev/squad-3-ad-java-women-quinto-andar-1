import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LogService } from '../services/log-service';
import { Log } from '../model/log';

@Component({
  selector: 'app-error-view',
  templateUrl: './error-view.component.html',
  styleUrls: ['./error-view.component.css']
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
    });
  }
}
