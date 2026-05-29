package com.kirandeep.ecommerce.product.service;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kirandeep.ecommerce.product.dto.ProductDto;
import com.kirandeep.ecommerce.product.entity.Product;
import com.kirandeep.ecommerce.product.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;
	
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

	public List<ProductDto> findByProductName(String name){
	List<Product> searchedProducts = productRepository.findByProductName(name);

return searchedProducts.stream()
		.map(product->modelMapper.map(product,ProductDto.class))
				.toList();
	}


}
