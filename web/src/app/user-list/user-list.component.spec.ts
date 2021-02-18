import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserListComponent } from './user-list.component';
import { MockUserService } from '../services/mock-user.service';

describe('UserListComponent', () => {
  let component: UserListComponent;
  let fixture: ComponentFixture<UserListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserListComponent ],
      providers: [{ provide: 'IUserResourceService', useClass: MockUserService}],
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
