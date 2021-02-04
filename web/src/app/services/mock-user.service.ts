import { Injectable, Optional } from '@angular/core';
import { Configuration, UserDTO, UserResourceServiceInterface } from '../integration';
import { Observable, of, throwError } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

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

  userdata: UserDTO[] = [{"id":1,"name":"Peter","password":undefined,"friends":[{"id":3,"name":"Daniel","password":undefined,"friends":undefined,"groups":undefined},{"id":5,"name":"Florian","password":undefined,"friends":undefined,"groups":undefined},{"id":2,"name":"Keti","password":undefined,"friends":undefined,"groups":undefined}],"groups":undefined},{"id":2,"name":"Keti","password":undefined,"friends":[{"id":1,"name":"Peter","password":undefined,"friends":undefined,"groups":undefined},{"id":4,"name":"Phillip","password":undefined,"friends":undefined,"groups":undefined}],"groups":undefined},{"id":3,"name":"Daniel","password":undefined,"friends":[{"id":5,"name":"Florian","password":undefined,"friends":undefined,"groups":undefined}],"groups":undefined},{"id":4,"name":"Phillip","password":undefined,"friends":undefined,"groups":undefined},{"id":5,"name":"Florian","password":undefined,"friends":[{"id":3,"name":"Daniel","password":undefined,"friends":undefined,"groups":undefined}],"groups":undefined}];

  authenticateUser(body?: UserDTO, extraHttpRequestParams?: any): Observable<UserDTO> {
    return throwError("not implemented");
  }

  getUserById(id: number, extraHttpRequestParams?: any): Observable<UserDTO> {
    return throwError("not implemented");
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
