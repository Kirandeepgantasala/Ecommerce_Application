import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomerDetails } from '../../auth/model/CustomerDetails';
import { Observable } from 'rxjs';
import { Address } from '../model/Address';
import { Order } from '../model/Order';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  url = "https://ecommerce-application-vjk3.onrender.com/customers"
  customerDetails!:CustomerDetails;
  constructor(private http:HttpClient) { }
   
  getUserProfile():Observable<CustomerDetails>{

return this.http.get<CustomerDetails>(`${this.url}/profile`);
    
  }

  getUserDetails():Observable<CustomerDetails>{

   return this.getUserProfile();
  }

   addAddress(address:Address):Observable<any>{
    return this.http.post<any>(`${this.url}/add-address`,address);
  }

  getAllAddresses():Observable<Address[]>{
    return this.http.get<Address[]>(`${this.url}/addresses-list`);
  }

   getAllCustomerOrders():Observable<Order[]>{
    return this.http.get<Order[]>(`${this.url}/getAllOrders`);
  }

}
