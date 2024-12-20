import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-myaccount',
  templateUrl: './myaccount.component.html',
  styleUrl: './myaccount.component.css'
})
export class MyaccountComponent implements OnInit {
  user:any={
    name:'',
    address:'',
    email:'',
    password:'',
  }

  username:any;
  constructor(private _user:UserService, private router:Router,private act:ActivatedRoute){}

  ngOnInit(): void {
    this.username=this.act.snapshot.paramMap.get('username');
   // console.log(this.username)
    this._user.getUserByUsername(this.username).subscribe(res=>{
      this.user=res;
      this.user.password='';
    //  console.log(this.user)
    })
  }

  modify(){
    this.username=this.act.snapshot.paramMap.get('username');
    this._user.updateByUsername(this.username,this.user).subscribe(res=>{
      this.router.navigate(['home']);
    })
  }
}
