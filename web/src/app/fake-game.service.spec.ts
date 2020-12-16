import {TestBed} from '@angular/core/testing';

import {FakeGameService} from './fake-game.service';

describe('GamesService', () => {
  let service: FakeGameService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FakeGameService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return 3 games', () => {
    service.getGames().subscribe(games => {
      expect(games.length).toBe(3);
    });
  });

});
