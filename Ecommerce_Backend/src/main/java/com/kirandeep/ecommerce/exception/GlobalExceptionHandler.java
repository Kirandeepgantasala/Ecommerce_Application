package com.kirandeep.ecommerce.exception;

import com.razorpay.RazorpayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

	}

	@ExceptionHandler(value = RazorpayException.class)
	public ResponseEntity<String> handleRazorPayException(RazorpayException razorpayException){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(razorpayException.getMessage());
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> handleException(Exception exception){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
}
