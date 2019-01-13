import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CoreEditorComponent } from './core-editor.component';

describe('CoreEditorComponent', () => {
  let component: CoreEditorComponent;
  let fixture: ComponentFixture<CoreEditorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CoreEditorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CoreEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
