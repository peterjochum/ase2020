import { Component, Inject, OnInit } from '@angular/core';
import { Game } from '../interfaces/game';
import { Router, ActivatedRoute } from '@angular/router';
import { IGameService } from '../interfaces/gameservice';

@Component({
  selector: 'app-game-detail',
  templateUrl: './game-detail.component.html',
  styleUrls: ['./game-detail.component.scss']
})
export class GameDetailComponent implements OnInit {


  game?: Game;

  constructor(@Inject('IGameService') private gameService: IGameService, private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    // @ts-ignore
    const gameId: number = Number(this.route.snapshot.paramMap.get('id'));
    this.gameService.get(gameId).subscribe(game => {
      this.game = game;
    });
  }

}
