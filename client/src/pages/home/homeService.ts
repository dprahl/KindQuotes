import{Http, Response, Headers} from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';

@Injectable()
export class HomeService{
    constructor(private _http:Http){

    }

    getAllQuotes() : Observable<any> {
        return this._http.get("http://localhost:8080/quotes/")
            .map(response => response.json());
    }

    getQuote(quoteId : number) : Observable<any> {
      return this._http.get("http://localhost:8080/quotes/" + quoteId)
            .map(response => response.json());
    }


}
