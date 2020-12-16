import { Injectable } from '@angular/core';
import {Game} from './game';

@Injectable({
  providedIn: 'root'
})
export class GamesService {

  constructor() { }

  FakeGames: Game[] = [
    {id: 1, name: 'Red Dead', year: 2017},
    {id: 2, name: 'Cyberpunk', year: 2020},
    {id: 3, name: 'Baba is you', year: 2019},
  ];

  getGames(): Game[] {
    return this.FakeGames;
  }
}
