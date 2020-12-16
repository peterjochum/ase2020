import { Component, OnInit } from '@angular/core';
import {Game} from '../game';
import {GameService} from '../game.service';

@Component({
  selector: 'app-games-list',
  templateUrl: './games-list.component.html',
  styleUrls: ['./games-list.component.scss']
})
export class GamesListComponent implements OnInit {

  games: Game[];

  constructor(private gameService: GameService) {
    this.games = [];
  }

  ngOnInit(): void {
    this.getGames();
  }

  getGames(): void {
    this.gameService.getGames().subscribe(games => this.games = games);
  }
}
