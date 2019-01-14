import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CoreEditComponent } from './core-edit.component';

describe('CoreEditComponent', () => {
  let component: CoreEditComponent;
  let fixture: ComponentFixture<CoreEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CoreEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CoreEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
