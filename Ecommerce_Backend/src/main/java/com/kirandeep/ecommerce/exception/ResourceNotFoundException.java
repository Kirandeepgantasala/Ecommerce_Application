package com.kirandeep.ecommerce.exception;

public class ResourceNotFoundException extends RuntimeException  {

	public ResourceNotFoundException() {
		
	}
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
