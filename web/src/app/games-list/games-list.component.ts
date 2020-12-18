import {Component, OnInit} from '@angular/core';
import {Game} from '../interfaces/game';
import {GameService} from '../services/game.service';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-games-list',
  templateUrl: './games-list.component.html',
  styleUrls: ['./games-list.component.scss']
})
export class GamesListComponent implements OnInit {

  games?: Game[];
  serviceError?: HttpErrorResponse;

  constructor(private gameService: GameService) {}

  ngOnInit(): void {
    this.getGames();
  }

  private getGames(): void {
    this.gameService.getGames().subscribe(games => this.games = games,
        error => this.serviceError = error);
  }
}
