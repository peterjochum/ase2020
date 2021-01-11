import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {User} from '../models/user';
import { JwtHelperService } from '@auth0/angular-jwt';



@Injectable({
  providedIn: 'root'
})
export class UserService {

   // Base URL
  baseUrl = "http://localhost:8080/Users/api/"; //check backend for right api
  //token = localStorage.getItem('token'); /* get token from localStorage */

  constructor(public http: HttpClient) { }


  /* Register user */
  signUp(user: User): Observable<any> {
        let apiUrl = this.baseUrl + "signup";
        let params = JSON.stringify(user);
        let headers = new HttpHeaders().set('Content-Type', 'application/json');
        return this.http.post(apiUrl, params, {headers: headers});
  }


   /* Check whether User is loggedIn */
   isLoggedIn(): boolean{
 		// create an instance of JwtHelper class.
    let jwtHelper = new JwtHelperService();
 		// get the token from the localStorage as we have to work on this token.
 		let token = localStorage.getItem('token');
 		// check whether if token is null.
 		if(!token){return false;}
    // check whether the token has expired
 		if(token){
 			let expirationDate = jwtHelper.getTokenExpirationDate(token);
 			let isExpired = jwtHelper.isTokenExpired(token);
 			return !isExpired;
 		}
    return true;
   }

  /* Login user */
  login(user: User): Observable<any> {
      let apiUrl = this.baseUrl + "login";
      let params = JSON.stringify(user);
      let headers = new HttpHeaders().set('Content-Type', 'application/json');
      return this.http.post(apiUrl, params, {headers: headers});
  }

  /* Update user */
  update(user: User): Observable<any> {
        let token = localStorage.getItem('token');
        if (token == null) {
            token = 'undefined';
        }
        let apiUrl = this.baseUrl + "update/" + user.id;
        let params = JSON.stringify(user);

       let headers = new HttpHeaders()
              .set('Content-Type', 'application/json')
              .set('Authorization', token);
          return this.http.put(apiUrl, params, {headers: headers});

    }

    /* get users */
    getUsers(): Observable<any> {
        let token = localStorage.getItem('token');
        if (token == null) {
            token = 'undefined';
        }
        let apiUrl = this.baseUrl + "users/"; // check again backend for right api

        let headers = new HttpHeaders()
              .set('Content-Type', 'application/json')
              .set('Authorization', token);
          return this.http.get(apiUrl, {headers: headers});

    }

}
