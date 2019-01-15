import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { CoreService } from '../core.service';
import { Core } from '../cores/shared/core.model';

@Component({
  selector: 'app-core-add',
  templateUrl: './core-add.component.html',
  styleUrls: ['./core-add.component.css']
})
export class CoreAddComponent implements OnInit {


  coreForm: FormGroup; 
  isLoadingResults = true;

  constructor(private router: Router,
              private coreService: CoreService,
              private fb: FormBuilder) { }

  ngOnInit() {
    this.coreForm =this.fb.group({
      name: ['', Validators.required],
      path: ['', Validators.required],
      keys: this.fb.array([])
    });
  }


  onFormSubmit(core: Core) {
    this.isLoadingResults = true;
    this.coreService.addCore(core)
    .subscribe(res => {
        let id = res['id'];
        this.isLoadingResults = false;
        this.router.navigate(['/core-details', id]);
      }, (err) => {
        console.log(err);
        this.isLoadingResults = false;
      });
  }
}
