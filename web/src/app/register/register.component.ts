import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import {User} from '../models/user';
import {UserService} from '../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  title = 'Sign Up';

  public user = new User();
  public status: string;


  constructor(private userService : UserService, private router : Router) { }

  ngOnInit(): void {
  }

  // create the form object.
   form = new FormGroup({
     username : new FormControl('' , Validators.required),
     password : new FormControl('' , Validators.required),
     passwordConf : new FormControl('' , Validators.required)
   });

   onSubmit(){
     let username = (<HTMLInputElement>document.getElementById("username")).value;
     let pass = (<HTMLInputElement>document.getElementById("password")).value;
     let confirmPass = (<HTMLInputElement>document.getElementById("passwordConf")).value;
     if(pass == confirmPass){
       this.user.name = username;
       this.user.password = pass;
       this.userService.signUp(this.user).subscribe(
          response => {
              let result = response.json();
              if(result > 0) {
                this.status = 'success';
                this.router.navigate(['/login']);
              }else{
                  this.status = 'error';
              }
          },
          error => {
              console.log("Error!");
          }
        );
    }else{
        this.status = 'passwordError';
    }
  }
}
