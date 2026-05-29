import { Component, OnInit } from '@angular/core';
import { ProductService } from '../service/product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../model/Product';
import { CommonModule } from '@angular/common';
import { CartService } from '../service/cart.service';
import { CategoryListComponent } from '../../shared/category-list/category-list.component';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule,CategoryListComponent],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit{
  products: Product[] = [];

  errorMessage:string='';
  categoryName:string='';
  isSpinnerEnabled:boolean =false;
  constructor(private productService:ProductService,private route:ActivatedRoute,private router:Router,private cartService:CartService){}

  ngOnInit()  {
    this.route.queryParamMap.subscribe(queryParams=>{
const keyword = queryParams.get('name');
console.log("keyword",keyword);
if(keyword){
  this.searchProductsByName(keyword);
}
else{
  this.route.paramMap.subscribe(params=>{
    const id = Number(params.get('id'));
    console.log("Category id",id);
    this.getProductsByCategoryId(id);
  })
}

    });

   this.categoryName= this.route.snapshot.paramMap.get('categoryName')||'';
  }
  searchProductsByName(keyword: string) {
    this.productService.getProductsByName(keyword).subscribe({
      next:(data)=>{
        this.products=data;
        this.isSpinnerEnabled=true;
        console.log("Products length",this.products.length);
        if(this.products.length===0){
          this.errorMessage="No Matching Products Found";
          console.log(this.errorMessage)
        }
      },
      error:(error)=>{
        this.isSpinnerEnabled=false;
        console.log("No Products Found",error);

      }
      
    });
  }

getProductsByCategoryId(id:number){
  this.productService.getProductsByCategoryId(id).subscribe({
    next:(data: Product[])=>{
      this.products=data;
    },
  error:(error: any)=>{
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
