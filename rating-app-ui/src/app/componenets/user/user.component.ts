import { Component, OnInit } from '@angular/core';
import { EmailValidator, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/entity/User';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {


  userList : User[]=[];
  userEntity : User[]=[];
  userFlag:string="users";
  userForm: FormGroup;

  constructor(private service: UserService, 
    private fb: FormBuilder

){

  this.userForm = this.fb.group({
    name: [Validators.required],
    email :[Validators.required],
    about: [Validators.required]
   })
}

  ngOnInit(): void {
    
    this.service.getAllUsers().subscribe(
      res=>{ 
        this.userList = res;
        console.log(this.userList);
      },
      error =>{ console.log(error); }
      
    )}

    addUser(){
    console.log(this.userForm.value);
    this.service.saveUser(this.userForm.value).subscribe(res =>{
      console.log(res);
    })
    }

    buttonClick(val:string){
        this.userFlag = val;
    }

}
