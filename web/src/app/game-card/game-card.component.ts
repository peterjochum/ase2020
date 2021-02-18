import { Component, Input, OnInit } from '@angular/core';
import { Game } from '../interfaces/game';
import { GameDTO } from '../integration';

@Component({
  selector: 'app-game-card',
  templateUrl: './game-card.component.html',
  styleUrls: ['./game-card.component.scss'],
})
/**
 * GameCardComponent shows a single game as a card element that can be used
 * in list or library views.
 */
export class GameCardComponent implements OnInit {
  /**
   * Game interface containing game data
   */
  @Input() game?: GameDTO;

  constructor() {}

  ngOnInit(): void {}
}
