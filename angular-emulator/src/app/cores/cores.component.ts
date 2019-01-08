import { Component, OnInit } from '@angular/core';
import { Core } from '../cores/shared/core.model';
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

  add(name: string, path: string): void {
    name = name.trim();
    path = path.trim();
    var core: Core = { name, path, keys: [] } as Core;
    
    if (!name) { return; }
    this.coreService.addCore(core)
      .subscribe(core => {
        this.cores.push(core);
      });
  }

  delete(core: Core): void {
    this.cores = this.cores.filter(c => c != core);
    this.coreService.deleteCore(core).subscribe();
  }
}
