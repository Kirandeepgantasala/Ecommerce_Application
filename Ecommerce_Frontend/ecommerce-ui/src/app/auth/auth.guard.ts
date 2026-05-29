import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';
import { inject } from '@angular/core';
import { Toast, ToastrService } from 'ngx-toastr';

export const authGuard: CanActivateFn = (route, state) => {

  const authService = inject(AuthService);
  const toast = inject(ToastrService);
const router = inject(Router);
  if(authService.isLoggedIn()){
    return true;
  }
  else{
    toast.error("Please Login To Continue");

    
    setTimeout(()=>{
      router.navigate(['/login']);
    },2000)
    
    return false;
  }
};


