import { Injectable } from '@angular/core';
import {Game} from './game';
import {Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor() { }

  FakeGames: Game[] = [
    {id: 1, name: 'Red Dead', year: 2017},
    {id: 2, name: 'Cyberpunk', year: 2020},
    {id: 3, name: 'Baba is you', year: 2019},
  ];

  getGames(): Observable<Game[]> {
    return of(this.FakeGames);
  }
}
