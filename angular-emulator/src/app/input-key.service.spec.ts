import { TestBed } from '@angular/core/testing';

import { InputKeyService } from './input-key.service';

describe('InputKeyService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InputKeyService = TestBed.get(InputKeyService);
    expect(service).toBeTruthy();
  });
});
