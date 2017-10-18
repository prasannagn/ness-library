import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {RequestOptions, Request, RequestMethod} from '@angular/http';
import {Observable} from "rxjs/Observable";
import {IBook} from "./book";
import {environment} from "../../environments/environment";
import "rxjs/add/observable/throw";
import "rxjs/add/operator/catch";
import "rxjs/add/operator/do";
import "rxjs/add/operator/map";

@Injectable()
export class BookService {
  private _bookUrl = environment.book_service_url + "/books";

  constructor(private _http:HttpClient) {
  }

  findAll():Observable<IBook[]> {
    return this._http.get<IBook[]>(this._bookUrl);
  }

  findOne(id:number):Observable<IBook> {
    return this._http.get<IBook>(this._bookUrl + "/" + id);
  }

  save(book:IBook) {
    return this._http.post<IBook>(this._bookUrl, book);
  }

  delete(id:number) {
    return this._http.delete<IBook>(this._bookUrl + "/" + id).subscribe(book=>console.log(book));
  }

  private handleError(err:HttpErrorResponse) {
    // in a real world app, we may send the server to some remote logging infrastructure
    // instead of just logging it to the console
    let errorMessage = '';
    if (err.error instanceof Error) {
      // A client-side or network error occurred. Handle it accordingly.
      errorMessage = `An error occurred: ${err.error.message}`;
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
    }
    console.error(errorMessage);
    return Observable.throw(errorMessage);
  }
}
