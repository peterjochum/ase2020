import { Injectable, Optional } from '@angular/core';
import { Configuration} from '../integration';
import { Observable, of, throwError } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { UserResourceServiceInterface } from '../integration/api/userResource.serviceInterface';
import { UserDTO } from '../integration/model/userDTO';
import { Group } from '../models/group';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class MockUserService implements UserResourceServiceInterface {

  public defaultHeaders = new HttpHeaders();
  public configuration = new Configuration();

  constructor(@Optional() configuration: Configuration) {
    if (configuration) {
      this.configuration = configuration
    }
  }

  gPuzzles = new Group(1, "Puzzle", "Puzzle group - puzzlers welcome", new User(1, "Peter", "", undefined, undefined))

  userdata: UserDTO[] = [{"id":1,"name":"Peter","password":undefined,"friends":[{"id":3,"name":"Daniel","password":undefined,"friends":undefined,"groups":undefined},{"id":5,"name":"Florian","password":undefined,"friends":undefined,"groups":undefined},{"id":2,"name":"Keti","password":undefined,"friends":undefined,"groups":undefined}],"groups":undefined},{"id":2,"name":"Keti","password":undefined,"friends":[{"id":1,"name":"Peter","password":undefined,"friends":undefined,"groups":undefined},{"id":4,"name":"Phillip","password":undefined,"friends":undefined,"groups":undefined}],"groups":undefined},{"id":3,"name":"Daniel","password":undefined,"friends":[{"id":5,"name":"Florian","password":undefined,"friends":undefined,"groups":undefined}],"groups":undefined},{"id":4,"name":"Phillip","password":undefined,"friends":undefined,"groups":undefined},{"id":5,"name":"Florian","password":undefined,"friends":[{"id":3,"name":"Daniel","password":undefined,"friends":undefined,"groups":undefined}],"groups":undefined}];

  authenticateUser(body?: UserDTO, extraHttpRequestParams?: any): Observable<UserDTO> {
    return throwError("not implemented");
  }

  getUserById(id: number, extraHttpRequestParams?: any): Observable<UserDTO> {
    console.log("Getting user "+ id);
    const user = this.userdata.find(x => x.id == id);
    if (user === undefined) {
      return throwError("user not found")
    }
    return of(user);
  }

  getUsers(extraHttpRequestParams?: any): Observable<Array<UserDTO>> {
    return of(this.userdata);
  }

  registerUser(body?: UserDTO, extraHttpRequestParams?: any): Observable<UserDTO> {
    return throwError("not implemented");
  }

  updateUser(body?: UserDTO, extraHttpRequestParams?: any): Observable<UserDTO> {
    return throwError("not implemented");
  }


}
