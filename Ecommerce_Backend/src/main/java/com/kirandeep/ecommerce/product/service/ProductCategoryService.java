package com.kirandeep.ecommerce.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kirandeep.ecommerce.product.dto.ProductCategoryDto;
import com.kirandeep.ecommerce.product.entity.ProductCategory;
import com.kirandeep.ecommerce.product.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {

	private ProductCategoryRepository productCategoryRepository;
	
	public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
		this.productCategoryRepository=productCategoryRepository;
	}
	
	public List<ProductCategoryDto> getAllCategories(){
	List<ProductCategory> allCategories = productCategoryRepository.findAll();
	
	return allCategories.stream()
			.map(this::mapToDto).
			toList();
	}
	
	private ProductCategoryDto mapToDto(ProductCategory productCategory) {
		ProductCategoryDto dto = new ProductCategoryDto();
		dto.setId(productCategory.getId());
		dto.setName(productCategory.getName());
		dto.setImageUrl(productCategory.getImageUrl());
		
		return dto;
	}
}
