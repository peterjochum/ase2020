import { Component, Inject, Input, OnInit } from '@angular/core';
import { Game } from '../interfaces/game';
import { HttpErrorResponse } from '@angular/common/http';
import { IGameService } from '../interfaces/gameservice';
import { GameDTO, GameResourceService, GameResourceServiceInterface } from '../integration';

@Component({
  selector: 'app-games-list',
  templateUrl: './games-list.component.html',
  styleUrls: ['./games-list.component.scss'],
})
export class GamesListComponent implements OnInit {
  /** games to be output in the list view */

  @Input()
  games?: GameDTO[];

  /** serviceError shows an alert on top of the component if set */
  serviceError?: HttpErrorResponse;

  /**
   * Initializes GamesListComponent with required services
   * @param gameService Service to retrieve the games from (set in environment)
   */
  constructor(@Inject('IGameService') private gameService: GameResourceServiceInterface) {}

  /**
   * ngOnInit retrieves games on component loading
   */
  ngOnInit(): void {
    if (this.games == undefined) {
      this.getGames();
    }
  }

  /**
   * getGames retrieves games from the IGameService or shows an alert
   * if there was an error getting the games
   * @private
   */
  private getGames(): void {
    this.gameService.getGames().subscribe(
      (games) => (this.games = games.slice(0,100)),
      (error) => (this.serviceError = error)
    );
  }
}
