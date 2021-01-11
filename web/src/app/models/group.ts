import {User} from './user';

export class Group {


        /* group's id */
        public id: number;
        /* group's name */
        public name: string;
        /* group's description */
        public description: string;
        /* group's owner*/
        public user: User;

}
