import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { LoginRequest } from '../model/login-request';
import { AuthNavbarComponent } from '../auth-navbar/auth-navbar.component';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { Router, RouterLink } from '@angular/router';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule,AuthNavbarComponent,CommonModule,RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  loginRequest: LoginRequest={email:'',password:''};

  isSpinnerEnabled:boolean = false;
  
  
  constructor(private formBuilder:FormBuilder,private authService:AuthService,private toast:ToastrService,private router:Router){}
  ngOnInit(){
this.loginForm = this.formBuilder.group({
email :['',[Validators.required,Validators.email]],
 password:['',[Validators.required]]
})
  }

  onSubmit(){
    if(this.loginForm.valid){
this.loginRequest=this.loginForm.value;
      this.authService.login(this.loginRequest).subscribe({
        next:(data)=>{
          this.isSpinnerEnabled=true;
          console.log("Login Successful",data)
          this.toast.success("Logged In Successfully");
           this.router.navigate(['']);
          
        },
        error:(error)=>{
          this.isSpinnerEnabled=false;
          console.log("Login Failed",error);
          this.toast.error("Login Failed!! Try again")
        }

    });
    }

  }

}
