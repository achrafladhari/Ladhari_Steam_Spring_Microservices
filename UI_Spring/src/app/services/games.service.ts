import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';
import {BehaviorSubject, Observable, Subject} from "rxjs";
import {isPlatformBrowser} from "@angular/common";
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GamesService {

  constructor(private http:HttpClient) {
  }

  private cart: any[] = [];
  private cartSubject = new BehaviorSubject<any[]>(this.cart);
  onAddToCart$ = this.cartSubject.asObservable();
  private gameUrl = environment.apiUrl+'/games';
  private gameAdminUrl = environment.apiUrl+'/game';
  private categoryUrl = environment.apiUrl+'/category';
  token = localStorage.getItem('token');

  // CATEGORY
  deleteCat(id:any){
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.token}`,
      'Content-Type': 'application/json',
    });
    return this.http.delete(this.categoryUrl+'/admin/'+id,{headers})
  }

    //get images!
    GetGamesImages(imageId:any): Observable<Blob>{
      return this.http.get(`${this.gameUrl}/${imageId}/image`,{responseType:'blob'});
    }


  addCategory(category:any){
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.token}`,
      'Content-Type': 'application/json',
    });
    return this.http.post(this.categoryUrl+'/admin',category,{headers})
  }


  updateCat(id:any,category:any){
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.token}`,
      'Content-Type': 'application/json',
    });
    return this.http.put(this.categoryUrl+'/admin/'+id,category,{headers})
  }

  getCategoryById(id:any){
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.token}`,
      'Content-Type': 'application/json',
    });
    return this.http.get(this.categoryUrl+'/admin/'+id,{headers})
  }


  getCategory(){
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.token}`,
      'Content-Type': 'application/json',
    });
    return this.http.get(this.categoryUrl+'/admin',{headers})
  }

  //CARTTTT METHODS
  getAllGames(){
    return this.http.get(this.gameUrl);
  }

  addToCart(game: any) {
    const gameExists = this.cart.some(g => g.id === game.id);
    if (!gameExists) {
      this.cart.push(game);
      this.cartSubject.next(this.cart); // Notify subscribers
    }
  }
  removeFromCart(gameId: any) {
    this.cart = this.cart.filter(g => g.id !== gameId);
    this.cartSubject.next(this.cart); // Notify subscribers of updated cart
  }

  clearFromCart(){
   this.cart= [];
   this.cartSubject = new BehaviorSubject<any[]>(this.cart);
  }

  getCartItems() {
    return this.cart; // Return current cart data
  }

//GAMES ROUTES

  deleteGame(id:any){
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.token}`,
      'Content-Type': 'application/json',
    });
    return this.http.delete(this.gameAdminUrl+'/admin/'+id,{headers})
  }

  getGameById(id:any){
    return this.http.get(this.gameUrl+'/'+id);
  }
  //CART METHOS !!!!!!

  modifyGameById(id:any,game:any){
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.token}`,
      'Content-Type': 'application/json',
    });
    return this.http.put(this.gameAdminUrl+'/admin/'+id,game,{headers})
  }

  findByName(name: string, page: number, size: number): Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`,
      'Content-Type': 'application/json'
    });

    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    if (name) {
      params = params.set('name', name);
    }
    return this.http.get(`${this.gameUrl}/pagination`, { headers, params });
  }

  postGame(game:any){
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.token}`
    });
    return this.http.post(this.gameAdminUrl+'/admin',game,{headers})
  }
}
