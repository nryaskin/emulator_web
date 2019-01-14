import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CoreAddComponent } from './core-add.component';

describe('CoreAddComponent', () => {
  let component: CoreAddComponent;
  let fixture: ComponentFixture<CoreAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CoreAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CoreAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
