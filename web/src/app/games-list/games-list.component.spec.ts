import {ComponentFixture, TestBed} from '@angular/core/testing';

import {GamesListComponent} from './games-list.component';
import {HttpClientModule} from '@angular/common/http';

describe('GamesListComponent', () => {
  let component: GamesListComponent;
  let fixture: ComponentFixture<GamesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GamesListComponent],
      imports: [HttpClientModule]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GamesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have 3 games listed', () => {
    const compiled = fixture.nativeElement;
    expect(compiled.querySelectorAll('.game-info').length).toBe(3);
  });
});
