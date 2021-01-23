import {Group} from './group';

export class User {
        /* user's id */
        id: number;
        /* users name */
        name: string;
        /* user's password */
        password: string;
        /* user's friend list*/
        friends: Array<User>;
        /* user's group list */
        groups: Array<Group>;

        constructor(id: number, name: string, password: string, friends?: Array<User>, groups?: Array<Group>) {
          this.id = id;
          this.name = name;
          this.password = password;
          this.friends = friends || new Array<User>();
          this.groups = groups || new Array<Group>();
        }
}
