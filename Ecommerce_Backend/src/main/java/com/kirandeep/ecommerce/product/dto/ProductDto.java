package com.kirandeep.ecommerce.product.dto;

public class ProductDto {

	 private Long id;
	    private String name;
	    private String description;
	    private Double price;
	    private String imageUrl;
	    private Boolean active;
	    private Integer unitsInStock;

	    private Long categoryId;
	    private String categoryName;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		public String getImageUrl() {
			return imageUrl;
		}
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		public Boolean getActive() {
			return active;
		}
		public void setActive(Boolean active) {
			this.active = active;
		}
		public Integer getUnitsInStock() {
			return unitsInStock;
		}
		public void setUnitsInStock(Integer unitsInStock) {
			this.unitsInStock = unitsInStock;
		}
		public Long getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}
		public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
	    
	    
}
