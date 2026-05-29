import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../auth/auth.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule,FormsModule,RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  keyword!: string;
  isActive = false;
  userLoggedIn:boolean = false;
  constructor(private router:Router,private authService:AuthService,private toast:ToastrService){
    this.isUserLoggedIn();
  }
  toggleSidebar() {
    console.log("Clicked")
      if(this.isActive){
        this.isActive=false;
      }else{
        this.isActive =true;
      }
      
    }

    search(event:Event){
      event.stopPropagation();
      this.router.navigate(['/search'],{
        queryParams:{name:this.keyword}
      });
    }


    logout(){
      this.authService.logOut();
      this.toast.success("Logged Out Successfully");
    this.router.navigate(['/login']);
    }

    isUserLoggedIn(){
      this.userLoggedIn= this.authService.isLoggedIn();
    }

    

}
