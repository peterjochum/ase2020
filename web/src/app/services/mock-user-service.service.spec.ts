import { TestBed } from '@angular/core/testing';

import { MockUserService } from './mock-user.service';

describe('MockUserServiceService', () => {
  let service: MockUserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MockUserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return some users', () => {
    service.getUsers().subscribe(users => {
      expect(users.length).toBe(5);
    });
  });
});
