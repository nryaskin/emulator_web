import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Core } from './core';
import { CORES } from './mock-cores'
import { MessageService } from './message.service'

const MESSAGE_FETCHED_ALL: string = 'CoreService: fetched cores'; 
const MESSAGE_FETCHED_BY_ID: string = 'CoreService: fetched core id=${id}';

@Injectable({
  providedIn: 'root'
})
export class CoreService {

  constructor(private messageService:MessageService) { }

  getCores(): Observable<Core[]> {
    this.messageService.add(MESSAGE_FETCHED_ALL);
    return of(CORES);
  }

  getCore(id: number): Observable<Core> {
    this.messageService.add(MESSAGE_FETCHED_BY_ID);
    return of(CORES.find(core => core.id === id));  
  }
}
