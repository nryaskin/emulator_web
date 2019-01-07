import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { InputKey } from './keys/shared/input-key.model'
import { CORES } from './mock/mock-cores'

@Injectable({
  providedIn: 'root'
})
export class InputKeyService {

  constructor() { }

  getKeysByCoreId(id: number): Observable<InputKey[]> {
    return of(CORES.find(core => core.id === id).keys);
  }
}
