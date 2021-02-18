import { Component, Inject, OnInit } from '@angular/core';
import { UserDTO, UserResourceServiceInterface } from '../integration';
import { faUser } from '@fortawesome/free-solid-svg-icons'


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  constructor(@Inject('IUserResourceService') private userService: UserResourceServiceInterface) { }

  faUser = faUser;
  users?: UserDTO[];

  ngOnInit(): void {
    this.userService.getUsers().subscribe((users) => {
      this.users = users;
    })
  }

}
