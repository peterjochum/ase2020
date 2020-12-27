import { TestBed } from '@angular/core/testing';

import { GameService } from './game.service';
import { HttpClientModule } from '@angular/common/http';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { ConfigService } from './config.service';
import { Config } from '../interfaces/config';
import { MockGameService } from './mock-game.service';
import { Game } from '../interfaces/game';

describe('GameService', () => {
  let service: GameService;
  let httpMock: HttpTestingController;
  let configServiceSpy: jasmine.SpyObj<ConfigService>;
  const fakeGamesUrl = '/api/games';
  const fakeConfig: Config = { gamesUrl: fakeGamesUrl };

  beforeEach(() => {
    const spy = jasmine.createSpyObj('ConfigService', ['getConfig']);
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [GameService, { class: ConfigService, useValue: spy }],
    });
    httpMock = TestBed.inject(HttpTestingController);
    configServiceSpy = TestBed.inject(
      ConfigService
    ) as jasmine.SpyObj<ConfigService>;
    service = TestBed.inject(GameService);
  });

  it('should be created', () => {
    expect(service).toBeDefined();
  });

  it('created a spie for the config service', () => {
    expect(configServiceSpy.getConfig).toBeDefined();
  });

  it('should get games from the service', () => {
    // Perform game api request
    spyOn(configServiceSpy, 'getConfig').and.returnValue(fakeConfig);
    service.getGames().subscribe((games) => {
      expect(games.length).toBe(2);
    });

    // Check the request
    const request = httpMock.expectOne(fakeGamesUrl);
    expect(request.request.method).toBe('GET');

    // Return test data
    const game1: Game = {
      id: 1,
      name: 'foo',
      text: 'bar',
      image: 'tan',
      year: 2020,
    };
    const game2: Game = game1;
    request.flush([game1, game2]);
  });

  afterEach(() => {
    // Make sure no requests are left
    httpMock.verify();
  });
});
