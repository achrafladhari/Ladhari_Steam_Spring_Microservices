import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {AuthService} from "./auth.service";
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LibraryService {

  constructor(private http:HttpClient) { }
  private url = environment.apiUrl+'/library'

  token = localStorage.getItem('token');

  getAllGames(username:any){
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.token}`,
      'Content-Type': 'application/json',
    });

    let params = new HttpParams()
    params=params.set('username',username)
    return this.http.get(this.url,{headers,params});
  }
}
