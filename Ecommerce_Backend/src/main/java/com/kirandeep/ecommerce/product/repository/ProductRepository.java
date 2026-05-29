package com.kirandeep.ecommerce.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kirandeep.ecommerce.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

	List<Product> findByCategoryId(Long categoryId);

	@Query(value = "SELECT * FROM PRODUCT WHERE NAME LIKE CONCAT('%',:productName,'%')",nativeQuery = true)
	List<Product> findByProductName(@Param("productName") String productName);
}
