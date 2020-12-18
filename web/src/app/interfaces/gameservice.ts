import { Game } from './game';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

export interface IGameService {
  getGames(): Observable<Game[]>;
}
