import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormArray, Validators } from '@angular/forms';

@Component({
  selector: 'app-core-editor',
  templateUrl: './core-editor.component.html',
  styleUrls: ['./core-editor.component.css']
})
export class CoreEditorComponent implements OnInit {

  coreForm = this.fb.group({
    name: ['', Validators.required],
    path: ['', Validators.required],
    keys: this.fb.array([
      this.fb.control('')
    ])
  });

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
  }

  get keys(): FormArray{
    return this.coreForm.get('keys') as FormArray;
  }
 
  addKey() {
    this.keys.push(this.fb.control(''));
  }
  
  onSubmit() {
    console.warn(this.coreForm.value);
  }
}
