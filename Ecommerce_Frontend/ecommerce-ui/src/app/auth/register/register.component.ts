import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { RegisterRequest } from '../model/register-request';
import { AuthService } from '../auth.service';
import { AuthNavbarComponent } from '../auth-navbar/auth-navbar.component';
import { ToastrService } from 'ngx-toastr';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule,AuthNavbarComponent,CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit {
  registerForm!:FormGroup;
  registerRequest:RegisterRequest={name:'',email:'',password:'',phoneNumber:''}
 isRegistering:boolean = false;
  constructor(private fb:FormBuilder,private authService:AuthService,
    private toast:ToastrService
  ){}
ngOnInit() {
  this.registerForm=this.fb.group({
name:['',[Validators.required]],
email:['',[Validators.required,Validators.email]],
phoneNumber:['',[Validators.required]],
password:['',[Validators.required]]
  });
}

onSubmit(){
  if(this.registerForm.valid){
    this.registerRequest=this.registerForm.value;
this.authService.register(this.registerRequest)

.subscribe({
  next:(data)=>{
    console.log("Registered Successfully",data);
    this.toast.success("Registered Successfully");
    this.isRegistering=true;
  },
  error:(error)=>{
    console.log("Unable to register,error")
    this.toast.error("Unable To Register");
    this.isRegistering=false;
  }
});
  }
}



}
