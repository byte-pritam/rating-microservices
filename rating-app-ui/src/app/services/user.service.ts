import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../entity/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private URL = "http://localhost:8081/users"

  constructor(private http: HttpClient) { }

  getAllUsers():Observable<any>{
    return this.http.get<any>(this.URL);
  }

  saveUser(user: User){
    return this.http.post(this.URL, user);
  }
  
}
