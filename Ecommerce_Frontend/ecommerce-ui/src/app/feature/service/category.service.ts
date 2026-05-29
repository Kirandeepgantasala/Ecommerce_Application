import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  url = "https://ecommerce-application-vjk3.onrender.com/categories";

  constructor(private http:HttpClient) { }

  getAllCategories():Observable<any[]>{
    return this.http.get<any[]>(this.url);
  }
}
