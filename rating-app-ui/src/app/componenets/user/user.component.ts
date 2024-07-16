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
  submitted: boolean =false;

  constructor(private service: UserService, 
    private fb: FormBuilder

){

  this.userForm = this.fb.group({
    name: ['', Validators.required],
    email :['',Validators.required],
    about: ['',Validators.required]
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
    
    this.submitted = true;
    if(this.userForm.valid){
      console.log(this.userForm.value);
      this.service.saveUser(this.userForm.value).subscribe(res =>{
        console.log(res);
      })
    }
    else{
      console.log("form not valid");
    }
   
    }

    buttonClick(val:string){
        this.userFlag = val;
    }

    clearField(){
     console.log("user click")
      this.userForm.reset();
      this.submitted= false;
      // this.userForm.value.name='';
      // this.userForm.value.email='';
      // this.userForm.value.about='';
    }

}
