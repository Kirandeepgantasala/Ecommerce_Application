package com.kirandeep.ecommerce.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kirandeep.ecommerce.product.dto.ProductDto;
import com.kirandeep.ecommerce.product.entity.Product;
import com.kirandeep.ecommerce.product.repository.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository){
		this.productRepository=productRepository;
	}
	
	public List<ProductDto> findProductsByCategory(Long id){
	
		List<Product> products = productRepository.findByCategoryId(id);

		return products
				.stream()
				.map(this::mapToDto).
				toList();
	}
	
	public ProductDto findProductByid(Long id) {
		Product product = productRepository.findById(id).orElse(null);
		return mapToDto(product);
	}
	private ProductDto mapToDto(Product product) {

	    ProductDto dto = new ProductDto();
	    dto.setId(product.getId());
	    dto.setName(product.getName());
	    dto.setDescription(product.getDescription());
	    dto.setPrice(product.getPrice());
	    dto.setImageUrl(product.getImageUrl());
	    dto.setActive(product.getActive());
	    dto.setUnitsInStock(product.getUnitsInStock());

	    dto.setCategoryId(product.getCategory().getId());
	    dto.setCategoryName(product.getCategory().getName());

	    return dto;
	}


}
