import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';
import { Profile } from '../shared/profile.module';
import { CoreService } from 'src/app/core.service';
import { Core } from 'src/app/cores/shared/core.model';

@Component({
  selector: 'app-profile-dialog',
  templateUrl: './profile-dialog.component.html',
})
export class ProfileDialogComponent implements OnInit {

  isCreate: boolean;

  cores: Core[] = [];

  constructor(@Inject(MAT_DIALOG_DATA) public profile: Profile,
              private coreService: CoreService) { }

  ngOnInit() {
    if (this.profile == null) {
      this.isCreate = true;
      this.profile = { id: undefined, name: '', core: null };
    } else {
      this.isCreate = false;
    }
    this.getCores();
  }

  getCores(): void {
    this.coreService.getCores()
    .subscribe(cores => {
                this.cores = cores;
               }, err => {
               });
  }

}
