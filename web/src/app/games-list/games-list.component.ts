import { Component, Inject, OnInit } from '@angular/core';
import { Game } from '../interfaces/game';
import { HttpErrorResponse } from '@angular/common/http';
import { IGameService } from '../interfaces/gameservice';

@Component({
  selector: 'app-games-list',
  templateUrl: './games-list.component.html',
  styleUrls: ['./games-list.component.scss'],
})
export class GamesListComponent implements OnInit {
  games?: Game[];
  serviceError?: HttpErrorResponse;

  constructor(@Inject('IGameService') private gameService: IGameService) {}

  ngOnInit(): void {
    this.getGames();
  }

  private getGames(): void {
    this.gameService.getGames().subscribe(
      (games) => (this.games = games),
      (error) => (this.serviceError = error)
    );
  }
}
