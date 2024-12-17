import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { NotfoundComponent } from './shared/notfound/notfound.component';

const routes: Routes = [
  {path:'',redirectTo:'/home',pathMatch:'full'},
  {path:'home',component:HomeComponent},
 /* {path:'update/article/:id',canActivate:[AuthGuard],component:CreatearticleComponent},
  {path:'update/author/:id',canActivate:[AuthGuard],component:RegisterComponent},
  {path:'create',canActivate:[AuthGuard],component:CreatearticleComponent},*/
 /* {path:'about',component:AboutComponent},
  {path:'privacy',component:PrivacyComponent},
  {path:'author/:id',component:AuthorComponent},*/
  {path:'login',component:LoginComponent},
  {path:'register',component:RegisterComponent},
  {path:'**',component:NotfoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
