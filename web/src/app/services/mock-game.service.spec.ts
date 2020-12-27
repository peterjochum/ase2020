import { TestBed } from '@angular/core/testing';

import { MockGameService } from './mock-game.service';

describe('MockGameService', () => {
  let service: MockGameService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MockGameService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return 4 games', () => {
    service.getGames().subscribe((games) => {
      expect(games.length).toBe(4);
    });
  });
});
