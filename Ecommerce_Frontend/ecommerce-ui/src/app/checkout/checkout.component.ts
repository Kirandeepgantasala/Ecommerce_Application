import { Component } from '@angular/core';
import { CartService } from '../cart.service';
import { CartItem } from '../model/CartItem';
import { CommonModule } from '@angular/common';
import { OrderItem } from '../model/OrderItem';
import { PlaceOrderRequest } from '../model/PlaceOrderRequest';
import { OrderService } from '../order.service';
import { Order } from '../model/Order';
import { Router } from '@angular/router';

@Component({
  selector: 'app-checkout',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './checkout.component.html',
  styleUrl: './checkout.component.css'
})
export class CheckoutComponent {

  cartItems:CartItem[]=[];
  totalPrice:number=0;
  orderItems:OrderItem[]=[];
  orderResponse!:Order;
  constructor(private cartService:CartService,private orderService:OrderService,private route:Router){
this.cartItems = this.cartService.getCart();
    this.totalPrice=this.cartService.showTotalPrice();
  }

  placeOrder(){
   this.orderItems = this.cartItems.map(item=>({
     productId:item.productId,
     price:item.price,
     quantity:item.quantity
    }))

     const orderRequest = {
    customerId: 1,
    totalPrice: this.totalPrice,
    totalQuantity: this.cartItems.reduce((total,item)=>total=total+item.quantity,0),
    orderItems: this.orderItems
  };

this.orderService.placeOrder(orderRequest).subscribe({
  next:(data)=>{
this.orderResponse=data;
console.log(data)
console.log('-------------------')
console.log(this.orderResponse)
this.route.navigate(['order-status',data.orderId]);
  },
  error:(error)=>{
    console.log("Error Occured",error);
  }
})


   

  }

  
 
 
    
 

}
