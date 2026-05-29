package com.kirandeep.ecommerce.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kirandeep.ecommerce.product.dto.ProductCategoryDto;
import com.kirandeep.ecommerce.product.entity.ProductCategory;
import com.kirandeep.ecommerce.product.service.ProductCategoryService;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins="http://localhost:4200")
public class ProductCategoryController {
	
	private ProductCategoryService productCategoryService;
	
	public ProductCategoryController(ProductCategoryService productCategoryService) {
		this.productCategoryService=productCategoryService;
	}
	

	@GetMapping
	public ResponseEntity<List<ProductCategoryDto>> getAllCategories()
	{
		List<ProductCategoryDto> allCategories = productCategoryService.getAllCategories();
		return ResponseEntity.ok(allCategories);
	}
}
