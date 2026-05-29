import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../service/product.service';
import { Product } from '../model/Product';
import { CartService } from '../service/cart.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent implements OnInit{
  product!: Product;
  constructor(private productService:ProductService,private route:ActivatedRoute,private cartService:CartService){}
  ngOnInit() {

    this.route.paramMap.subscribe(params=>{
     let productId:number =  Number(params.get('id'));
this.getProductDetailsById(productId);
    })

  }

  getProductDetailsById(id:number){
    this.productService.getProductById(id).subscribe({
      next:(data: any)=>{
        this.product=data;
      },
      error:(error: string)=>{
        console.log("Error occured"+error);
      }
    })
  };
  
  addToCart(product:Product){
    this.cartService.addToCart(product);
  }

}
