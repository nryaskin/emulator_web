import { Component, OnInit } from '@angular/core';
import { Core } from '../core';
import { CoreService } from '../core.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  cores: Core[] = [];  

  constructor(private coreService: CoreService) { }

  ngOnInit() {
    this.getCores();
  }

  getCores(): void {
    this.coreService.getCores()
      .subscribe(cores => this.cores = cores.slice(1, 5));
  }

}
