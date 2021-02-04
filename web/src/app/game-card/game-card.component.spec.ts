import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameCardComponent } from './game-card.component';
import { RouterTestingModule } from '@angular/router/testing';

describe('GameCardComponent', () => {
  let component: GameCardComponent;
  let fixture: ComponentFixture<GameCardComponent>;
  let nativeElement: any;
  const testGame = {
    id: 12,
    name: 'Testname',
    image: 'http://example.com/img.jpg',
    text: 'Testdescription',
    year: 2020,
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      declarations: [GameCardComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GameCardComponent);
    component = fixture.componentInstance;
    component.game = testGame;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeDefined();
  });

  it('should show that no game was passed', () => {
    // clear game to show warning
    component.game = undefined;
    fixture.detectChanges();

    // check if warning is shown
    const warningElement: HTMLElement = fixture.nativeElement.querySelector(
      '.alert'
    );
    expect(warningElement.textContent).toContain('No game supplied');
  });

  it('should show the games title', () => {
    const titleElement: HTMLElement = fixture.nativeElement.querySelector(
      '.card-title'
    );
    expect(titleElement.textContent).toContain(testGame.name);
  });

  it('should show the year of publication', () => {
    const yearElement: HTMLElement = fixture.nativeElement.querySelector(
      '.year-badge'
    );
    expect(yearElement.textContent).toBe(testGame.year.toString());
  });

  it('should show a title image', () => {
    const imageElement: HTMLElement = fixture.nativeElement.querySelector(
      '.card-img-top'
    );
    expect(imageElement.getAttribute('src')).toBe(testGame.image);
  });

  it('should show an alternative text in the title image', () => {
    const imageElement: HTMLElement = fixture.nativeElement.querySelector(
      '.card-img-top'
    );
    const expectedAltText = testGame.name + ' title image';
    expect(imageElement.getAttribute('alt')).toBe(expectedAltText);
  });
});
