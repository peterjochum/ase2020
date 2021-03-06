import { Injectable, Optional } from '@angular/core';
import { Game } from '../interfaces/game';
import { Observable, of, throwError } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { Configuration, GameCollectionDTO, GameDTO, GameResourceServiceInterface } from '../integration';


@Injectable({
  providedIn: 'root',
})
export class MockGameService implements GameResourceServiceInterface {

  FakeGames: Game[] = [
    {
      id: 1,
      name: 'Cyberpunk 2077',
      year: 2020,
      image: 'https://images.igdb.com/igdb/image/upload/t_cover_big/co2mjs.jpg',
      text:
        'Cyberpunk 2077 is a role-playing video game developed and published by CD Projekt. ' +
        'Adapted from the Cyberpunk franchise, the game is an open world, non-linear RPG with ' +
        'an FPS style in which players are able to heavily customize their character to suit ' +
        'their play style. Gun play, exploration, player choice and activities such as hacking ' +
        'are to feature heavily throughout the game with missions, quests and objectives being ' +
        'completed in a variety of different ways. The world will have dynamic weather and a ' +
        'day/night cycle to make it truly immersive.',
    },
    {
      id: 2,
      name: 'The Witcher 3: Wild Hunt',
      year: 2015,
      image: 'https://images.igdb.com/igdb/image/upload/t_cover_big/co1wyy.jpg',
      text:
        'RPG and sequel to The Witcher 2 (2011), The Witcher 3 follows witcher Geralt of Rivia as' +
        'he seeks out his former lover and his young subject while intermingling with the political' +
        'workings of the wartorn Northern Kingdoms. Geralt has to fight monsters and deal with people' +
        'of all sorts in order to solve complex problems and settle contentious disputes, each ' +
        'ranging from the personal to the world-changing.',
    },
    {
      id: 3,
      name: 'Hollow Knight: Silksong',
      year: 2021,
      image: 'https://images.igdb.com/igdb/image/upload/t_cover_big/co1jb4.jpg',
      text:
        'Explore a vast, haunted kingdom in Hollow Knight: Silksong, the sequel to the award winning ' +
        'action-adventure! Discover enchanting secrets and face foes in lethal, acrobatic combat as ' +
        'you ascend to the peak of a land ruled by silk and song.\n\n' +
        'Hollow Knight: Silksong is the epic sequel to Hollow Knight, the epic action-adventure of ' +
        'bugs and heroes. As the lethal hunter Hornet, journey to all-new lands, discover new powers, ' +
        'battle vast hordes of bugs and beasts and uncover ancient secrets tied to your nature' +
        'and your past.',
    },
    {
      id: 4,
      name: 'The Elderscrolls VI',
      year: 2220,
      image: 'https://images.igdb.com/igdb/image/upload/t_cover_big/co1ycv.jpg',
      text: 'The long awaited next installment in the Elder Scrolls franchise.',
    },
  ];


  public defaultHeaders = new HttpHeaders();
  public configuration = new Configuration();

  constructor(@Optional() configuration: Configuration) {
    if (configuration) {
      this.configuration = configuration;
    }
  }

  addGame(id: number, gameId: number, extraHttpRequestParams?: any): Observable<GameCollectionDTO> {
    throw new Error('Method not implemented.');
  }

  deleteGame(id: number, gameId: number, extraHttpRequestParams?: any): Observable<GameCollectionDTO> {
    throw new Error('Method not implemented.');
  }

  getGame(id: number, extraHttpRequestParams?: any): Observable<GameDTO> {
    const game = this.FakeGames.find(x => x.id == id);
    if (game === undefined) {
      return throwError('game not found');
    }
    return of(game);
  }

  getGamecollection(id: number, extraHttpRequestParams?: any): Observable<GameCollectionDTO> {
    throw new Error('Method not implemented.');
  }


  getSuggestedGames(id: number, extraHttpRequestParams?: any): Observable<GameDTO[]> {
    return of(this.FakeGames.slice(0,2));
  }

  getGames(page?: number, extraHttpRequestParams?: any): Observable<Array<GameDTO>> {
    return of(this.FakeGames);
  }

  getGamesByName(name: string, page?: number, extraHttpRequestParams?: any): Observable<Array<GameDTO>> {
    throw new Error('Method not implemented.');
  }
}
