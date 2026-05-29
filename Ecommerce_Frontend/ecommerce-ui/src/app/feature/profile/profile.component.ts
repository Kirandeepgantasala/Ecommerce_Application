import { Component } from '@angular/core';
import { CustomerService } from '../service/customer.service';
import { CustomerDetails } from '../../auth/model/CustomerDetails';
import { AuthService } from '../../auth/auth.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  customer!:CustomerDetails;

  constructor(private customerService:CustomerService,private router:Router,private authService:AuthService,private toast:ToastrService){
    this.getProfile();
  }


  getProfile(){
    this.customerService.getUserProfile().subscribe({
      next:(data: CustomerDetails)=>{
        console.log("next invoked in profile")

        this.customer=data;
        console.log("User Profile Fetched Successfully");

      },
      error:(error: any)=>{
        console.log("Unable to fetch customer profile")
        console.log(error);
      }
    })
  }

  logout(){
    this.authService.logOut();
    this.toast.success("Logged Out Successfully");
    this.router.navigate(['/login']);
  }

}
