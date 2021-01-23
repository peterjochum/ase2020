import { Group } from './group';
import { User } from './user';

describe('Group', () => {
  it('should create an instance', () => {
    const owner = new User(1, "Bill", "password?");
    expect(new Group(1, "Testgroup", "A testgroup", owner)).toBeTruthy();
  });
});
