import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Entity } from './entity.model';
import { Observable, of, throwError } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    Authorization: '345pasdf2340986asdf'
  })
};

const entityUrl = 'http://localhost:8081/restapi/entidad';

@Injectable({
  providedIn: 'root'
})
export class EntityService {

  constructor(private http: HttpClient) {}

  public getEntities(): Observable<any> {
    console.log('getEntities called');
    return this.http.get(`${entityUrl}`, httpOptions);
  }

  public getEntity(id: number): Observable<Entity> {
    const url = `${entityUrl}/${id}`;
    console.log('getEntity called');
    return this.http.get<Entity>(url, httpOptions).pipe(
      tap(_ => console.log(`fetched entity id=${id}`)),
      catchError(this.handleError<Entity>(`getEntity id=${id}`))
    );
  }

  public createEntity(entity: Entity) {
    return this.http.post<Entity>(entityUrl, entity);
  }

  public deleteEntityById(id: number) {
    const url = `${entityUrl}/${id}`;
    return this.http.delete(url, httpOptions);
  }

  public updateEntity(entity: Entity): Observable<any> {
    const url = `${entityUrl}/${entity.id}`;
    return this.http.put(url, entity, httpOptions).pipe(
      tap(_ => console.log(`updated product id=${entity.id}`)),
      catchError(this.handleError<any>('updateEntity'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
