import { Component, OnInit } from '@angular/core';
import { InputKey } from '../keys/shared/input-key.model'
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { InputKeyService } from '../input-key.service'

@Component({
  selector: 'app-keys',
  templateUrl: './keys.component.html',
  styleUrls: ['./keys.component.css']
})
export class KeysComponent implements OnInit {

  inputKeys: InputKey[];

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private inputKeyService: InputKeyService
  ) { }

  ngOnInit() {
    this.getKeys();
  }

  getKeys(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.inputKeyService.getKeysByCoreId(id).subscribe(keys => this.inputKeys = keys);
  }

  goBack() {
    this.location.back();
  }
}
