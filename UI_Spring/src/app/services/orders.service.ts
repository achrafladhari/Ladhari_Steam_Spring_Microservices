import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private http:HttpClient) { }
  private url='http://localhost:8222/api/v1/orders'
  token = localStorage.getItem('token');

  createOrder(order:any){
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.token}`,
    });

    return this.http.post(`${this.url}`,order,{headers});
  }

  getUserOrders(username:any){
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.token}`,
    });
    return this.http.get(`${this.url}/${username}`,{headers});
  }
}
