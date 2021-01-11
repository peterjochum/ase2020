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
}
