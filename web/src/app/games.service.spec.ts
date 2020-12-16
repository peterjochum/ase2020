import {TestBed} from '@angular/core/testing';

import {GameService} from './game.service';

describe('GamesService', () => {
  let service: GameService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GameService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return 3 games', () => {
    service.getGames().subscribe(games => {
      console.log(games);
      expect(games.length).toBe(3);
    });
  });

});
