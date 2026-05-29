import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PlaceOrderRequest } from '../model/PlaceOrderRequest';
import { Observable } from 'rxjs';
import { Order } from '../model/Order';
import { OrderResponse } from '../model/OrderResponse';
import { PaymentsResponse } from '../model/PaymentsResponse';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
url = "https://ecommerce-application-vjk3.onrender.com/orders"
  constructor(private http:HttpClient) { }

  placeOrder(orderRequest:PlaceOrderRequest):Observable<Order>{
    return this.http.post<Order>(this.url+'/placeOrder',orderRequest);
  }

  getOrderDetails(orderId:number):Observable<Order>{
    return this.http.get<Order>(this.url+'/'+orderId);
  }

  createOrder(placeOrderRequest:PlaceOrderRequest):Observable<OrderResponse>{
return this.http.post<OrderResponse>(`${this.url}/placeOrder`,placeOrderRequest);
  }

  verifyOrder(paymentResponse:PaymentsResponse):Observable<any>{
return this.http.post<any>(`${this.url}/verifyPayment`,paymentResponse);
  }

 

 

}
