import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable, throwError} from 'rxjs';
import {catchError, retry} from 'rxjs/operators';
import {Config} from '../interfaces/config';

@Injectable({
  providedIn: 'root',
})
/**
 * ConfigService allows retrieval of config information during runtime.
 */
export class ConfigService {
  /** configUrl is the location of the config file */
  configUrl = 'assets/config.json';

  /** config is the main configuration object */
  private config?: Config;

  constructor(private http: HttpClient) {
  }

  /**
   * loadConfig gets the configuration file. It returns a promise to let
   * the application initializer (app.module.ts) know when the Config is ready.
   */
  loadConfig(): Promise<Config> {
    return this.http.get<Config>('/assets/config.json').toPromise().then(config => this.config = config);
  }

  /**
   * getConfig returns a config object
   */
  getConfig(): Config {
    if (!this.config) {
      throw Error('Config file not loaded!');
    }
    return this.config;
  }
}
