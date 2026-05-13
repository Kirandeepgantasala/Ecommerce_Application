import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from './model/Product';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
url = "http://localhost:8080/products/";
  constructor(private http:HttpClient) { }


  getProductsByCategoryId(id: number):Observable<Product[]>{
    return this.http.get<Product[]>(this.url+'category/'+id)
  }

  getProductById(id:number):Observable<Product>{

    return this.http.get<Product>(this.url+id);
  }

}
