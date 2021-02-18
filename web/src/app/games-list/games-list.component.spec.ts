import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GamesListComponent } from './games-list.component';
import { HttpClientModule } from '@angular/common/http';
import { MockGameService } from '../services/mock-game.service';
import { IGameService } from '../interfaces/gameservice';
import { GameCardComponent } from '../game-card/game-card.component';
import { throwError } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';
import { FontAwesomeTestingModule } from '@fortawesome/angular-fontawesome/testing';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

describe('GamesListComponent', () => {
  let component: GamesListComponent;
  let fixture: ComponentFixture<GamesListComponent>;

  beforeEach(() => {
    // const spy = jasmine.createSpyObj('IGameService', 'getGames');
    TestBed.configureTestingModule({
      declarations: [GamesListComponent, GameCardComponent],
      imports: [HttpClientModule, RouterTestingModule],
      providers: [{ provide: 'IGameService', useClass: MockGameService }],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GamesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeDefined();
  });

  it('should have 4 games listed', () => {
    const compiled = fixture.nativeElement;
    expect(compiled.querySelectorAll('.card').length).toBe(4);
  });

  // it('should set its service error if there is a problem', () => {
  //   // TODO: fix this test
  //   fixture.componentInstance.gameService.getGames = function() {
  //     return throwError({status: 404});
  //   }
  //   component.ngOnInit();
  //   expect(component.serviceError?.error).toBe(404);
  // });
});
