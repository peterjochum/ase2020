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
  /** games to be output in the list view */
  games?: Game[];

  /** serviceError shows an alert on top of the component if set */
  serviceError?: HttpErrorResponse;

  /**
   * Initializes GamesListComponent with required services
   * @param gameService Service to retrieve the games from (set in environment)
   */
  constructor(@Inject('IGameService') private gameService: IGameService) {}

  /**
   * ngOnInit retrieves games on component loading
   */
  ngOnInit(): void {
    this.getGames();
  }

  /**
   * getGames retrieves games from the IGameService or shows an alert
   * if there was an error getting the games
   * @private
   */
  private getGames(): void {
    this.gameService.getGames().subscribe(
      (games) => (this.games = games),
      (error) => (this.serviceError = error)
    );
  }
}
