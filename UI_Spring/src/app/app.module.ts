import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HeaderComponent } from './shared/header/header.component';
import { FooterComponent } from './shared/footer/footer.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { OrdersComponent } from './components/orders/orders.component';
import { InventoryComponent } from './components/inventory/inventory.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NotfoundComponent } from './shared/notfound/notfound.component';
import { GameDetailComponent } from './components/game-detail/game-detail.component';
import { CreategameComponent } from './components/admin/games/creategame/creategame.component';
import { CreatecategoryComponent } from './components/admin/category/createcategory/createcategory.component';
import { MyaccountComponent } from './components/myaccount/myaccount.component';
import { CartComponent } from './shared/cart/cart.component';
import {GamesService} from "./services/games.service";
import { PaymentsComponent } from './components/payments/payments.component';
import { UsersComponent } from './components/admin/users/users.component';
import { AddmodifyComponent } from './components/admin/users/addmodify/addmodify.component';
import { GamesComponent } from './components/admin/games/games.component';
import { CategoryComponent } from './components/admin/category/category.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    OrdersComponent,
    InventoryComponent,
    NotfoundComponent,
    GameDetailComponent,
    CreategameComponent,
    CreatecategoryComponent,
    MyaccountComponent,
    CartComponent,
    PaymentsComponent,
    UsersComponent,
    AddmodifyComponent,
    GamesComponent,
    CategoryComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatMenuModule,
    MatButtonModule,
    MatIconModule
  ],
  providers: [GamesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
