import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';
import { CartItem } from '../model/CartItem';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent implements OnInit{
  constructor(private cartService:CartService,private route:Router){}

  cartItems:CartItem[]=[];
  totalPrice:number=0;
  ngOnInit() {
    
   this.cartItems = this.cartService.getCart();
   console.log(this.cartItems)
   this.showTotalPrice();
  }

  increaseQuantity(id:number){
    this.cartService.increaseQuantity(id);
    this.cartItems = this.cartService.getCart();
     this.showTotalPrice();
  }

  decreaseQuantity(id:number){
    this.cartService.decreaseQuantity(id);
    this.cartItems = this.cartService.getCart();
     this.showTotalPrice();
  }
removeItemFromCart(id:number){
  this.cartService.removeItemFromCart(id);
  this.cartItems = this.cartService.getCart();
   this.showTotalPrice();
}

showTotalPrice(){
this.totalPrice = this.cartService.showTotalPrice();
}

proceedToPayment(){
  this.route.navigate(['checkout']);
}


}
