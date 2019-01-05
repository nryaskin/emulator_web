import { Component, OnInit, Input } from '@angular/core';
import { Core } from '../core'
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { CoreService } from '../core.service';

@Component({
  selector: 'app-core-detail',
  templateUrl: './core-detail.component.html',
  styleUrls: ['./core-detail.component.css']
})
export class CoreDetailComponent implements OnInit {

  @Input() core: Core;  

  constructor(
    private route: ActivatedRoute,
    private coreService: CoreService,
    private location: Location  
  ) { }

  ngOnInit() {
    this.getCore();
  }

  getCore(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.coreService.getCore(id)
      .subscribe(core => this.core = core);
  }

  goBack(): void {
    this.location.back();
  }

}
