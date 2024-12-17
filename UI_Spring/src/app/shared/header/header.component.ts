import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit{
  constructor(public _auth:AuthService,private router:Router){}

  ngOnInit(): void {}
  logout(){
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

}
