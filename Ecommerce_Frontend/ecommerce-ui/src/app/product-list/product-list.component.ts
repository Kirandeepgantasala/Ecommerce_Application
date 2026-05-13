import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../model/Product';
import { CommonModule } from '@angular/common';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit{
  products: Product[] = [];
  constructor(private productService:ProductService,private route:ActivatedRoute,private router:Router,private cartService:CartService){}

  ngOnInit()  {
     this.route.paramMap.subscribe(params=>{
      let categoryId:number = Number(params.get('id'));
      this.getProductsByCategoryId(categoryId);
    })
  }

getProductsByCategoryId(id:number){

  

  this.productService.getProductsByCategoryId(id).subscribe({
    next:(data)=>{
      this.products=data;
    },
  error:(error)=>{
    console.log("Error Occured",error);
  }});
}

getProductDetails(id:number){
  this.router.navigate(['products/',id]);
}

addToCart(product:Product,event:Event){
  event.stopPropagation();
  this.cartService.addToCart(product);
}


}
