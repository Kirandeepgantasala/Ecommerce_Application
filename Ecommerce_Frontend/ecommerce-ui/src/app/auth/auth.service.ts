import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { AuthResponse } from './model/auth-response';
import { LoginRequest } from './model/login-request';
import { RegisterRequest } from './model/register-request';
import { RegisterResponse } from './model/register-response';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) { }

  private url = "http://localhost:8080";
  authResponse:AuthResponse={token:'',email:'',role:''}

  login(loginRequest:LoginRequest):Observable<AuthResponse>{

    return this.http.post<AuthResponse>(`${this.url}/auth/login`,loginRequest).pipe(
      tap((response)=>{
this.authResponse=response
localStorage.setItem("userDetails",JSON.stringify(this.authResponse));
      })
    );
  }

  register(registerRequest:RegisterRequest):Observable<RegisterResponse>{
    return this.http.post<RegisterResponse>(`${this.url}/auth/register`,registerRequest);
  }

  isLoggedIn():boolean{
    
const isTokenExpired:boolean =this.isTokenExpired();  
if(isTokenExpired){
  this.logOut()
  return false;
} 
 return true;

  }
  logOut(){
    localStorage.removeItem("userDetails")
   
  }

  getToken(){
    const userDetails = localStorage.getItem("userDetails");
      if(userDetails){
      const parsedData = JSON.parse(userDetails);
      const token = parsedData.token || null;
      return token;
    }
    return null;
  }


  isTokenExpired():boolean{
    const token=this.getToken();
    if(!token){
return true;
    }
    try{
 const expiry = JSON.parse(atob(token.split('.')[1].replace(/-/g, '+').replace(/_/g, '/'))).exp * 1000;


    const isExpired =   Date.now() >= expiry;
    if(isExpired){
      localStorage.removeItem("userDetails");
    }
    return isExpired;
    }
    catch(e){
      localStorage.removeItem("userDetails");
return true;
    }
   
  }

 
}
