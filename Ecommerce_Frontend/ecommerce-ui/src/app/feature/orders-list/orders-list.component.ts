import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../service/customer.service';
import { Order } from '../model/Order';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-orders-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './orders-list.component.html',
  styleUrl: './orders-list.component.css'
})
export class OrdersListComponent implements OnInit {

  orders:Order[]=[];
  constructor(private customerService : CustomerService){}
  ngOnInit() {
    this.getAllOrders();
    console.log(this.getAllOrders())
   
  }

  getAllOrders(){
    this.customerService.getAllCustomerOrders().subscribe({
      next:(data)=>{
        this.orders=data;
        console.log("Users Orders: ")
        console.log(this.orders);

      },
      error:(error)=>{
        console.log(error);

      }

    })
  }



}
