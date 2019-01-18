import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { API_URL } from '../../backend-defs';
import { MessageService } from 'src/app/message.service';
import { Observable, of } from 'rxjs';
import { Profile } from './profile.module';
import { catchError, map, tap } from 'rxjs/operators';

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
export class ProfileService {

  constructor(private http: HttpClient,
              private messageService: MessageService) { }

  private log(message: string) {
    this.messageService.add(`ProfileService: ${message}`);
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

    // TODO: send the error to remote logging infrastructure
    console.error(error); // log to console instead

    // TODO: better job of transforming error for user consumption
    this.log(`${operation} failed: ${error.message}`);

    // Let the app keep running by returning an empty result.
    return of(result as T);
    }
  }

  getProfilesPage(page: number, size: number): Observable<any>{
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<any>(`${API_URL}/profile`, {headers: httpOptionsCommon.headers, params: params})
      .pipe(
        tap(_=>this.log('fetched profiles')),
        catchError(this.handleError<Profile[]>('getProfilesPage', []))
      );
  }

  getProfile(id: number): Observable<Profile> {
    const url = `${API_URL}/profile/${id}`;
    return this.http.get<Profile>(url, httpOptionsCommon).pipe(
        tap(_ => this.log(`fetched profiles id=${id}`)),
        catchError(this.handleError<Profile>('getProfile id=${id}'))
      );
  }

  updateProfile(profile: Profile): Observable<any> {
    const url = `${API_URL}/profile/${profile.id}`;
    return this.http.put(url, profile, httpOptions).pipe(
      tap(_ => this.log(`updated profile id=${profile.id}`)),
      catchError(this.handleError<any>('updateProfile'))
    );
  }

  addProfile(profile: Profile): Observable<Profile> {
    return this.http.post<Profile>(`${API_URL}/profile`, profile, httpOptions)
      .pipe(
        tap((profile: Profile) => this.log(`added profile w/ id=${profile.id}`)),
        catchError(this.handleError<Profile>('addProfile'))
      );
  }

  deleteProfile(profile: Profile): Observable<Profile> {
    const id = typeof profile === 'number' ? profile: profile.id;
    const url = `${API_URL}/profile/${id}`;

    return this.http.delete<Profile>(url, httpOptions).pipe(
      tap(_ => this.log(`deleted profile id=${id}`)),
      catchError(this.handleError<Profile>('deleteProfile'))
    );
  }

}
