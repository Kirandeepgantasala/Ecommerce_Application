import { Routes } from '@angular/router';
import { ProductListComponent } from './feature/product-list/product-list.component';
import { ProductDetailsComponent } from './feature/product-details/product-details.component';
import { CategoryListComponent } from './shared/category-list/category-list.component';
import { CartComponent } from './feature/cart/cart.component';
import { CheckoutComponent } from './feature/checkout/checkout.component';
import { OrderStatusComponent } from './feature/order-status/order-status.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { HomeComponent } from './feature/home/home.component';
import { PaymentSuccessComponent } from './feature/payment-success/payment-success.component';
import { PaymentFailureComponent } from './feature/payment-failure/payment-failure.component';
import { OrdersListComponent } from './feature/orders-list/orders-list.component';
import { authGuard } from './auth/auth.guard';
import { ProfileComponent } from './feature/profile/profile.component';

export const routes: Routes = [
    {path:'',pathMatch:'full',component:HomeComponent},
    {
        path:'categories/:id/:categoryName',component:ProductListComponent
    },
    {
        path:'products/:id',component:ProductDetailsComponent
    }
    ,{
        path:'cart',component:CartComponent,canActivate:[authGuard]
    }
    ,{
        path:'checkout',component:CheckoutComponent,canActivate:[authGuard]
    },
    {
        path:'order-status/:id',component:OrderStatusComponent
    },
    {
        path:'login',component:LoginComponent
    },
    {
        path:'register',component:RegisterComponent
    },
    {path:'navbar',component:NavbarComponent}
    ,
    {
        path:'search',component:ProductListComponent
    },
    {
        path:'payment-success',component:PaymentSuccessComponent
    },
    {
        path:'payment-failed',component:PaymentFailureComponent
    },
    {
        path:'orders',component:OrdersListComponent,canActivate:[authGuard]
    },
    {
        path:'profile',component:ProfileComponent,canActivate:[authGuard]
    }
];
