import { Routes } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { CategoryListComponent } from './category-list/category-list.component';
import { CartComponent } from './cart/cart.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { OrderStatusComponent } from './order-status/order-status.component';

export const routes: Routes = [
    {path:'',component:CategoryListComponent},
    {
        path:'categories/:id',component:ProductListComponent
    },
    {
        path:'products/:id',component:ProductDetailsComponent
    }
    ,{
        path:'cart',component:CartComponent
    }
    ,{
        path:'checkout',component:CheckoutComponent
    },
    {
        path:'order-status/:id',component:OrderStatusComponent
    }
];
