import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PlaceOrderRequest } from './model/PlaceOrderRequest';
import { Observable } from 'rxjs';
import { Order } from './model/Order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
url = "http://localhost:8080/orders"
  constructor(private http:HttpClient) { }

  placeOrder(orderRequest:PlaceOrderRequest):Observable<Order>{
    return this.http.post<Order>(this.url+'/placeOrder',orderRequest);
  }

  getOrderDetails(orderId:number):Observable<Order>{
    return this.http.get<Order>(this.url+'/'+orderId);
  }

}
