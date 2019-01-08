import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Core } from './cores/shared/core.model';
import { MessageService } from './message.service';
import { API_URL } from './backend-defs';

const MESSAGE_FETCHED_ALL: string = 'CoreService: fetched cores'; 
const MESSAGE_FETCHED_BY_ID: string = 'CoreService: fetched core id=${id}';
const httpOptionsCommon = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin':'*'
  })
};
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class CoreService {

  constructor(private http: HttpClient,
              private messageService:MessageService) { }


  private log(message: string) {
    this.messageService.add(`CoreService: ${message}`);
  }

/**
 * Handle Http operation that failed.
 * Let the app continue.
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
  private handleError<T> (operation = 'operation', result?: T) {
      return (error: any): Observable<T> => {
 
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
 
      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);
 
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  getCores(): Observable<Core[]> {
    return this.http.get<Core[]>(`${API_URL}/core`, httpOptionsCommon)
      .pipe(
        tap(_ => this.log('fetched cores')),
        catchError(this.handleError('getCores', []))
      );
  }

  getCore(id: number): Observable<Core> {
    const url = `${API_URL}/core/${id}`;
    return this.http.get<Core>(url, httpOptionsCommon).pipe(
        tap(_ => this.log(`fetched cores id=${id}`)),
        catchError(this.handleError<Core>('getCore id=${id}'))
      );
  }
  
  updateCore(core: Core): Observable<any> {
    const url = `${API_URL}/core/${core.id}`;
    return this.http.put(url, core, httpOptions).pipe(
      tap(_ => this.log(`updated core id=${core.id}`)),
      catchError(this.handleError<any>('updateCore'))
    );
  }

  addCore(core: Core): Observable<Core> {
    return this.http.post<Core>(`${API_URL}/core`, core, httpOptions)
      .pipe(
        tap((core: Core) => this.log(`added core w/ id=${core.id}`)),
        catchError(this.handleError<Core>('addCore'))
      );
  }

  deleteCore(core: Core): Observable<Core> {
    const id = typeof core === 'number' ? core: core.id;
    const url = `${API_URL}/core/${id}`;

    return this.http.delete<Core>(url, httpOptions).pipe(
      tap(_ => this.log(`deleted core id=${id}`)),
      catchError(this.handleError<Core>('deleteCore'))
    );
  }
}
