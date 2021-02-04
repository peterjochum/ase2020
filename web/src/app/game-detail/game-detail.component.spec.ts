import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameDetailComponent } from './game-detail.component';
import { MockGameService } from '../services/mock-game.service';
import { RouterTestingModule } from '@angular/router/testing';

describe('GameDetailComponent', () => {
  let component: GameDetailComponent;
  let fixture: ComponentFixture<GameDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      declarations: [ GameDetailComponent ],
      providers: [{provide: 'IGameService', useClass: MockGameService}],
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GameDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
