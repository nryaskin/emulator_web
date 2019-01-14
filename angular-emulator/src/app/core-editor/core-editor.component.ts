import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormArray, Validators } from '@angular/forms';
import { CoreService } from '../core.service';

@Component({
  selector: 'app-core-editor',
  templateUrl: './core-editor.component.html',
  styleUrls: ['./core-editor.component.css']
})
export class CoreEditorComponent implements OnInit {


  constructor(private coreService: CoreService) { }

  ngOnInit() {
  }

  onClear() {
    this.coreService.coreForm.reset();
    this.coreService.initFormGroup();
  }

}
