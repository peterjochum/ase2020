import { Injectable } from '@angular/core';
import {Game} from '../interfaces/game';
import {Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MockGameService {

  constructor() { }

  FakeGames: Game[] = [
    {id: 1, name: 'Red Dead', image: 'foo', text: 'bang', year: 2017},
    {id: 2, name: 'Cyberpunk', image: 'foo', text: 'bang', year: 2020},
    {id: 3, name: 'Baba is you', image: 'foo', text: 'bang', year: 2019},
  ];

  getGames(): Observable<Game[]> {
    return of(this.FakeGames);
  }
}
