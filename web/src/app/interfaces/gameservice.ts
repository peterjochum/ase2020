import { Game } from './game';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

/**
 * IGameService encapsulates required functions of a video game service.
 */
export interface IGameService {
  /**
   * getGames retrieves a list of Game objects
   */
  getGames(): Observable<Game[]>;

  /**
   * get gets a single Game by id
   * @param id the id of the game
   */
  get(id: number) : Observable<Game>;
}
