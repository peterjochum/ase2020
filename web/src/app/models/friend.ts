export class Friend {

       public id: number;
       public userId: number;
       public friendId: number;

       constructor(id: number, userId: number, friendId: number) {
         this.id = id;
         this.userId = userId;
         this.friendId = friendId;
       }
}
