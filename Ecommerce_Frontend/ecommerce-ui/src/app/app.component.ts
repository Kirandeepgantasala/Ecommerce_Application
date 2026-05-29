import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { CategoryListComponent } from './shared/category-list/category-list.component';
import { ProductListComponent } from './feature/product-list/product-list.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,CategoryListComponent,ProductListComponent,NavbarComponent,CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ecommerce-ui';
  constructor(private router:Router){}

  showNavbar():boolean{

    return !(
      this.router.url.includes('/cart')
      ||
      this.router.url.includes('/checkout')
      ||
      this.router.url.includes('/payment-success')
      ||
      this.router.url.includes('/payment-failed')
      ||
      this.router.url.includes('/login')
      ||
      this.router.url.includes('/register')
    );

  }
}
