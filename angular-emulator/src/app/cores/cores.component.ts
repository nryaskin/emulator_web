import { Component, OnInit } from '@angular/core';
import { Core } from '../core';
import { CORES } from '../mock-cores';

@Component({
  selector: 'app-cores',
  templateUrl: './cores.component.html',
  styleUrls: ['./cores.component.css']
})
export class CoresComponent implements OnInit {


  cores = CORES;
  selectedCore: Core;

  constructor() { }

  ngOnInit() {
  }

  onSelect(core: Core): void {
    this.selectedCore = core;
  }

}
