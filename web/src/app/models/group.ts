import {User} from './user';

export class Group {
        /* group's id */
        public id: number;
        /* group's name */
        public name: string;
        /* group's description */
        public description: string;
        /* group's owner*/
        public user: User; // TODO: PJ - rename to "owner"

        constructor(id: number, name: string, description: string, user: User) {
          this.id = id;
          this.name = name;
          this.description = description;
          this.user = user;
        }
}
