import { Component, OnInit, Input } from '@angular/core';
import { Core } from '../core'

@Component({
  selector: 'app-core-detail',
  templateUrl: './core-detail.component.html',
  styleUrls: ['./core-detail.component.css']
})
export class CoreDetailComponent implements OnInit {

  @Input() core: Core;  

  constructor() { }

  ngOnInit() {
  }

}
