import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Log } from '../../src/main/java/br.com.report.entity/Log';
import {LogService} from '../../src/main/java/br.com.report.service/impl/LogService';

@Component({
  selector: 'app-error-view',
  templateUrl: './error-view.component.html',
  styleUrls: ['./error-view.component.css']
})
export class ErrorViewComponent implements OnInit {

  constructor(private router: Router, private logService: LogService) { }

  ngOnInit() {
    this.logService.findById()
    .subscribe(data => {
    this.log = data;
    });
  };

}
