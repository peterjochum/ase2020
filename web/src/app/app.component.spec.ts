import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';
import { GamesListComponent } from './games-list/games-list.component';
import { HttpClientModule } from '@angular/common/http';
import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MockGameService } from './services/mock-game.service';
import { GameCardComponent } from './game-card/game-card.component';

describe('AppComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule, HttpClientModule],
      declarations: [
        AppComponent,
        GamesListComponent,
        GameCardComponent,
        FooterComponent,
        NavbarComponent,
      ],
      providers: [{ provide: 'IGameService', useClass: MockGameService }],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have the steambuddy title`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('STEAMBUDDY');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('.navbar-brand').textContent).toContain(
      'STEAMBUDDY'
    );
  });
});
