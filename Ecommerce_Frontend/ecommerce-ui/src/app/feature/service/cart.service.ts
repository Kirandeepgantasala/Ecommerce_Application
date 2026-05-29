import { inject, Injectable } from '@angular/core';
import { ProductService } from './product.service';
import { CartItem } from '../model/CartItem';
import { Product } from '../model/Product';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class CartService {
   toast = inject(ToastrService);

cartItems:CartItem[]=[];



  constructor() { 
    this.loadCartFromLocalStorage();
  }
 
 addToCart(product:Product){

   const cartItem:CartItem={
      productId:product.id,
      productName:product.name,
      price:product.price,
      quantity:1,
      imageUrl:product.imageUrl

     }
     this.toast.success("Item Added to the cart")

     const existingItem = this.cartItems.find(item=>item.productId===cartItem.productId);

     if(existingItem){
      existingItem.quantity+=1;
      this.toast.success("Cart is Updated")
      console.log(this.cartItems);
      this.saveCartToLocalStorage();
     }
     else{
      console.log(this.cartItems);
      // this.cartItems.push(cartItem);
      this.addItem(cartItem);
     }

}
saveCartToLocalStorage(){
  localStorage.setItem('cartItems',JSON.stringify(this.cartItems));
}
loadCartFromLocalStorage(){
 const savedItems = localStorage.getItem('cartItems');
 if(savedItems){
  this.cartItems = JSON.parse(savedItems);
 }

}

 addItem(item:CartItem){
this.cartItems.push(item);
this.saveCartToLocalStorage();
 }

 getCart():CartItem[]{
  return this.cartItems;
 }

 increaseQuantity(id:number){

    let cartItem = this.cartItems.find(cartItem=>cartItem.productId===id);
    if(cartItem){
      cartItem.quantity+=1;
      this.saveCartToLocalStorage();
    }
  }

  decreaseQuantity(id:number){
 let cartItem = this.cartItems.find(cartItem=>cartItem.productId===id);
    if(cartItem&&cartItem.quantity>1){
      cartItem.quantity-=1;
      this.saveCartToLocalStorage();
    }
  }

  removeItemFromCart(id:number){
let cartItem = this.cartItems.find(cartItem=>cartItem.productId===id);
if(cartItem){
  this.cartItems=this.cartItems.filter(item=>item.productId!=id);
  this.saveCartToLocalStorage();
}
  }

  showTotalPrice():number{
   const totalPrice =  this.cartItems.reduce((total, product) => {
       return total + product.price * product.quantity;
    }, 0);
    return totalPrice;
  }

  clearCart(){
   this.cartItems=[];
      localStorage.removeItem('cartItems');
    
  }


}



