import { Component, OnInit } from '@angular/core';
import { Core } from '../core';
import { CoreService } from '../core.service'

@Component({
  selector: 'app-cores',
  templateUrl: './cores.component.html',
  styleUrls: ['./cores.component.css']
})
export class CoresComponent implements OnInit {


  cores: Core[];

  constructor(private coreService: CoreService) { }

  ngOnInit() {
    this.getCores();
  }

  getCores(): void {
    this.coreService.getCores()
      .subscribe(cores => this.cores = cores);
  }

}
