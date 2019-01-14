import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CoreService } from '../core.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { Core } from '../cores/shared/core.model';

@Component({
  selector: 'app-core-edit',
  templateUrl: './core-edit.component.html',
  styleUrls: ['./core-edit.component.css']
})
export class CoreEditComponent implements OnInit {

  coreForm: FormGroup;
  _id:string='';
  name:string='';
  path:string='';
  isLoadingResults = false;
  
  constructor(private router: Router,
              private route: ActivatedRoute,
              private coreService: CoreService,
              private fb: FormBuilder) { }

  ngOnInit() {
    this.getCore(this.route.snapshot.params['id']);
    this.coreForm = this.fb.group({
      name: ['', Validators.required],
      path: ['', Validators.required],
      keys: this.fb.array([
        this.fb.control('')
      ])
    });
  }

  getCore(id) {
  this.coreService.getCore(id).subscribe(core => {
    this._id = core.id.toString();
    this.coreForm.setValue({
          name: core.name,
          path: core.path,
          keys: [],
      });
    });
  }

  onFormSubmit(form: NgForm) {
    this.isLoadingResults = true;
    let update: Core = form.value() as Core;
    update.id = +this._id;
    this.coreService.updateCore(update)
      .subscribe(res => {
        let id = res['_id'];
        this.isLoadingResults = false;
        this.router.navigate(['/core-details', id]);
        }, (err) => {
          this.isLoadingResults = false;
        }
    );
  }

  coreDetails() {
    this.router.navigate(['/core-details', this._id]);
  }
}
