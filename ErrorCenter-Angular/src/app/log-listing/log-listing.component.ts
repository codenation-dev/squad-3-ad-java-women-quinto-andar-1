import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Log } from '../model/log';
import { LogService } from '../services/log.service';

@Component({
  selector: 'app-log-listing',
  templateUrl: './log-listing.component.html',
  styleUrls: ['./log-listing.component.css']
})

export class LogListingComponent implements OnInit {
  
  log: Log [];
  logs = this.log;  
  
  constructor(private router: Router, private logService: LogService) { }

  ngOnInit() 
   {
    this.logService.getLogs()
      .subscribe( data => {
        this.log = data;
      });
  }

  addLog(): void {
    this.logService.addLog(this.log)
        .subscribe( data => {
          alert("Log added succesfully.");
        });
  }
  
  changeStatus(log: Log): void {
    this.logService.changeStatus(log)
      .subscribe( data => {
        this.log = this.log.filter(u => u !== log);
      })
  };

  getLogById(id: number): Log {
    this.logService.getLogById(id)
  }

  findLogByEnvironment(environment: String) {
    this.findLogByEnvironment(environment);
  }
  
  findLogByEnvironmentAndOrderBy(environment: String, orderBy: String){
    this.findLogByEnvironmentAndOrderBy(environment,orderBy);
  }

  findLogByEnvironmentAndSearchBy(environment: String, searchBy: String){
    this.findLogByEnvironmentAndSearchBy(environment, searchBy);
  }
  findLogByEnvironmentAndOrderByAndSearchBy(environment: String, orderBy: String, searchBy: String) {
    this.findLogByEnvironmentAndOrderByAndSearchBy(environment, orderBy, searchBy);
  }


}
