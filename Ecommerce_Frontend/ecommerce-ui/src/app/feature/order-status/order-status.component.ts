import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderService } from '../service/order.service';
import { Order } from '../model/Order';
import { CartService } from '../service/cart.service';

@Component({
  selector: 'app-order-status',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './order-status.component.html',
  styleUrl: './order-status.component.css'
})
export class OrderStatusComponent implements OnInit {
  orderId!:number;
  orderDetails!:Order;
constructor(private orderService:OrderService, private route:ActivatedRoute,private cartService:CartService){}
  ngOnInit() {
    this.route.paramMap.subscribe(param=>{
this.orderId = Number(param.get('id'));
this.orderService.getOrderDetails(this.orderId).subscribe({
  next:(data: any)=>{
    this.orderDetails=data;
    console.log(this.orderDetails);
    this.cartService.clearCart();
    
  },
  error:(error: any)=>{
    console.log("Error occured",error);
  }
})
    })
  }



}
