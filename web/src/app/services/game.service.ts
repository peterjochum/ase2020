import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ConfigService } from './config.service';
import { Observable } from 'rxjs';
import { Game } from '../interfaces/game';
import { IGameService } from '../interfaces/gameservice';

@Injectable({
  providedIn: 'root',
})
/**
 * GameService provides access to game information located at a web service
 */
export class GameService implements IGameService {

  constructor(private http: HttpClient, private configService: ConfigService) {}

  /**
   * getGames retrieves a list of games from the web service.
   * @return a list of games
   */
  getGames(): Observable<Game[]> {
    return this.http.get<Game[]>(this.configService.getConfig().gamesUrl);
  }

  get(id: number): Observable<Game> {
    return this.http.get<Game>(this.configService.getConfig().gamesUrl + "/" + id);
  }


}
