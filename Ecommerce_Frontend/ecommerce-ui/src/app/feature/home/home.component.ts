import { Component } from '@angular/core';
import { NavbarComponent } from '../../shared/navbar/navbar.component';
import { ProductDetailsComponent } from '../product-details/product-details.component';
import { CategoryListComponent } from '../../shared/category-list/category-list.component';
import { ProductListComponent } from '../product-list/product-list.component';
import { BannerComponent } from '../../shared/banner/banner.component';
import { FeaturedComponent } from '../../shared/featured/featured.component';
import { FooterComponent } from '../../shared/footer/footer.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NavbarComponent,CategoryListComponent,BannerComponent,FeaturedComponent,FooterComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
