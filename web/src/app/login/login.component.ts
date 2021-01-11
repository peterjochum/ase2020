import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import {User} from '../models/user';
import {UserService} from '../services/user.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  title = 'Login';

  public user = new User();
  public status: string;

  constructor(private userService : UserService, private router : Router) {}

  // check first if user is already logged in, if yes redirect to profile pg
  ngOnInit(): void {
    if((this.userService.isLoggedIn()) ) {
        this.router.navigate(['/profile' , localStorage.getItem('id')]);
    } else {
        this.router.navigate(['/login']);
    }
  }

  // create the form object.
   form = new FormGroup({
     username : new FormControl('' , Validators.required),
     password : new FormControl('' , Validators.required)
   });

   onSubmit(){
        var inputName = (<HTMLInputElement>document.getElementById("username")).value;
        this.user.name = inputName;
        var inputPassword = (<HTMLInputElement>document.getElementById("password")).value;
        this.user.password = inputPassword;
        this.userService.login(this.user).subscribe(
          response => {
              let result =  response.json();

              if(result > 0) {
                let token = response.headers.get("Authorization");

                localStorage.setItem("token" , token);
                localStorage.setItem("id" , result);

                this.router.navigate(['/profile', result]);
                this.status = 'success';
              }
              if(result == -1) {
                this.status = 'error';
              }
          },
          error => {
              console.log("Error in authentication");
          }
        );
      }
}
