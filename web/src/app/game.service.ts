import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Config} from './config';
import {ConfigService} from './config.service';
import {Observable} from 'rxjs';
import {Game} from './game';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  config: Config;

  constructor(private http: HttpClient, private configService: ConfigService) {
    this.config = {gamesUrl: ''};
    configService.getConfig().subscribe(config => {
      this.config = config;
    });
  }

  getGames(): Observable<Game[]> {
    return this.http.get<Game[]>('http://localhost:8080/games');
  }

}
