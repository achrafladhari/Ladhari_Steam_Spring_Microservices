import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import {GamesService} from "../../services/games.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent implements OnInit{
  username:any
  constructor(public _auth:AuthService,private router:Router){
  }
  ngOnInit(
  ) {
    this.username=this._auth.getUserDataFromToken().sub
  }

  logout(){
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

}
