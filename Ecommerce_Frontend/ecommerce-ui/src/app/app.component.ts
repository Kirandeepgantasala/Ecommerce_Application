import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CategoryListComponent } from './category-list/category-list.component';
import { ProductListComponent } from './product-list/product-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,CategoryListComponent,ProductListComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ecommerce-ui';
}
