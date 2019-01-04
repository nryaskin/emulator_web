import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CoreDetailComponent } from './core-detail.component';

describe('CoreDetailComponent', () => {
  let component: CoreDetailComponent;
  let fixture: ComponentFixture<CoreDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CoreDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CoreDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
