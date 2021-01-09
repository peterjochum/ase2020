import { BrowserModule } from '@angular/platform-browser';
import { APP_INITIALIZER, NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GamesListComponent } from './games-list/games-list.component';
import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { environment } from '../environments/environment';
import { ConfigService } from './services/config.service';
import { GameCardComponent } from './game-card/game-card.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
//import { APIS, BASE_PATH } from './integration';

@NgModule({
  declarations: [
    AppComponent,
    GamesListComponent,
    NavbarComponent,
    FooterComponent,
    GameCardComponent,
    HomeComponent,
    RegisterComponent,
    LoginComponent,
  ],
  imports: [BrowserModule, HttpClientModule, AppRoutingModule],
  providers: [
    {
      provide: 'IGameService',
      useClass: environment.gameService,
    },
    {
      provide: APP_INITIALIZER,
      multi: true,
      deps: [ConfigService],
      useFactory: (configService: ConfigService) => {
        return () => {
          return configService.loadConfig();
        };
      },
    }//,
    //{ provide: BASE_PATH, useValue: 'http://localhost:8080/' },
    //APIS
  ],
  bootstrap: [AppComponent, GamesListComponent],
})
export class AppModule {}
