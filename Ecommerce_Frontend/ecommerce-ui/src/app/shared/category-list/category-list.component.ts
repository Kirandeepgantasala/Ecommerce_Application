import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../feature/service/category.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-category-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './category-list.component.html',
  styleUrl: './category-list.component.css'
})
export class CategoryListComponent implements OnInit {

  categories:any[] = [];
  constructor(private categoryService:CategoryService,private router:Router){}
  ngOnInit() {
    this.getCategoriesList();
  }
  getCategoriesList(){
    this.categoryService.getAllCategories().subscribe({
      next : (data)=>{
        this.categories=data;
      },
      error: (error)=>{
  console.log("Error",error);
      }
    });
    };

    getProductsByCategory(id:number,name:string){
      
this.router.navigate(['/categories',id,name]);
    }
  
}

