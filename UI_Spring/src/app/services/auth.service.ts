import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) {
   }

   private url = environment.apiUrl+'/auth/'

   register(user:any){
    return this.http.post(this.url+'register',user);
  }




  login(user:any){
    return this.http.post(this.url+'login',user);
  }

  isLoggedIn(){
    let token=localStorage.getItem('token');
    if(token){
      return true;
    }else{
      return false;
    }
  }
  getUserDataFromToken(){
    let token=localStorage.getItem('token');
    if(token){
      let data=JSON.parse(window.atob(token.split('.')[1]))
      return data;
    }
  }

}
