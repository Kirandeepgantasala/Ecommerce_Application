import { Component, OnInit } from '@angular/core';

import { ProductService } from '../../feature/service/product.service';
import { Product } from '../../feature/model/Product';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-featured',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './featured.component.html',
  styleUrl: './featured.component.css'
})
export class FeaturedComponent implements OnInit {
 products:Product[]=[];
 featuredProducts:Product[]=[];
  constructor(private productService:ProductService){}
  ngOnInit() {
    console.log("Featured ngOnInit");
    this.productService.getProductsByCategoryId(5).subscribe({
      next:(data)=>{
        if(data){
          console.log(data);
          this.products=data;
        }
        
        if(this.products.length>0){
          this.featuredProducts=this.products.slice(0,4);
        }
        else{
          console.log("No Featured Products");
        }
        
        console.log(this.featuredProducts)
      },
      error:(error)=>{
        console.log("Unable to fetch products",error)
      }
    })
  }


}
