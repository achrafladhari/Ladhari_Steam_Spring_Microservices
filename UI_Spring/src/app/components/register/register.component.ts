import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit{
  err=undefined;
  token:any;
  user:any={
    name:'',
    username:'',
    email:'',
    password:'',
    address:{
      street:'',
      zipCode:''
    }
  }
  constructor(private _auth:AuthService){}

  image:any=undefined;
  select(e:any){
    this.image=e.target.files[0];
  }
  ngOnInit(): void {
  }
  register(){
    let fd=new FormData()
    fd.append('name',this.user.name)
    fd.append('username',this.user.username)
    fd.append('email',this.user.email)
    fd.append('password',this.user.password)
    fd.append('address',this.user.address)
    fd.append('file',this.image)
    this._auth.register(fd)
    .subscribe(
      res=>{
        this.token=res;
        localStorage.setItem('token',this.token)
            }
            ,err=> {
        this.err=err;
      }
    )
  }
}
