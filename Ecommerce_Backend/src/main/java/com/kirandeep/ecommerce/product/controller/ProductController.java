package com.kirandeep.ecommerce.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kirandeep.ecommerce.product.dto.ProductDto;
import com.kirandeep.ecommerce.product.service.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService=productService;
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<ProductDto>> findProductsByCategoryId(@PathVariable Long categoryId){
		
		List<ProductDto> products =  productService.findProductsByCategory(categoryId);
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductDto> findProductById(@PathVariable Long productId){
		ProductDto searchedProduct = productService.findProductByid(productId);
		return ResponseEntity.ok(searchedProduct);
	}


	@GetMapping("/search")
	public ResponseEntity<List<ProductDto>> findProductByName(@RequestParam String name){
		List<ProductDto> productDtos = productService.findByProductName(name);
		return ResponseEntity.ok(productDtos);
	}
	
}
