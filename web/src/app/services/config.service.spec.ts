import { TestBed } from '@angular/core/testing';

import { ConfigService } from './config.service';
import { Config } from '../interfaces/config';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';

describe('ConfigService', () => {
  let service: ConfigService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    httpMock = TestBed.inject(HttpTestingController);
    service = TestBed.inject(ConfigService);
  });

  it('should be created', () => {
    expect(service).toBeDefined();
  });

  it('should throw an error if loadConfig has not been called', () => {
    expect(() => {
      service.getConfig();
    }).toThrow(new Error('config file not loaded'));
  });

  it('should be ready after loading a config file', () => {
    let testGamesUrl = 'http://example.com/api/';

    // Use service loadconfig to fetch the url
    service.loadConfig().then(() => {
      const config = service.getConfig();
      expect(config).toBeDefined();
      expect(config.gamesUrl).toBe(testGamesUrl);
    });

    // Expect the service to make tthe following request
    const request = httpMock.expectOne('/assets/config.json');
    expect(request.request.method).toBe('GET');

    // Provide the service with test data
    const testConfig: Config = { gamesUrl: testGamesUrl };
    request.flush(testConfig);
  });

  afterEach(() => {
    // Make sure no outstanding requests are present
    httpMock.verify();
  });
});
