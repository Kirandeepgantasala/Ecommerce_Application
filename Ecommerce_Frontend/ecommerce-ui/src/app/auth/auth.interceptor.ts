import { HttpInterceptorFn } from '@angular/common/http';
import { AuthService } from './auth.service';
import { inject } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { catchError, throwError } from 'rxjs';
console.log("Interceptor File Loaded");
export const authInterceptor: HttpInterceptorFn = (req, next) => {
  console.log("Interceptor Running");
  const authService = inject(AuthService);
const token = authService.getToken();

const toast = inject(ToastrService);

console.log("Token:", token);
console.log("TOKEN VALUE =", token);
const isUserLoggedIn = authService.isLoggedIn();
  if(isUserLoggedIn){
    const clonedRequest = req.clone({
      setHeaders: {
         Authorization: `Bearer ${token}`
      }
     
    });

    return next(clonedRequest);
  }

  return next(req).pipe(
    catchError((error)=>{
console.log(error)
      if(error.status===401){
        toast.error("Session Expired")
        setTimeout(()=>{
          authService.logOut();
        },2000)
      }

      return throwError(()=>error);

    })
  )


};
