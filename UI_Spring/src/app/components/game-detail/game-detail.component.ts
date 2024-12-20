import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {GamesService} from "../../services/games.service";

@Component({
  selector: 'app-game-detail',
  templateUrl: './game-detail.component.html',
  styleUrl: './game-detail.component.css'
})
export class GameDetailComponent implements OnInit {
  game: any;
  id: any;
  gameAdded: any;
  constructor(private _game: GamesService, private act: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.id = this.act.snapshot.paramMap.get('id');
    this._game.getGameById(this.id)
      .subscribe(res => {
        this.game = res;
      })
  }
  addToCart(game: any) {
    this._game.addToCart(game);
    this.gameAdded=`${game.name} added to cart.`;
  }
}
