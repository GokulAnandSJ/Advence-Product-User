package com.example.demo.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.ProductIdNotFoundException;


@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> productIdNotFound(ProductIdNotFoundException exc){
		
		ErrorStructure<String> er = new ErrorStructure<>();
		er.setErrorStatusCode(HttpStatus.NOT_FOUND.value());
		er.setErrorMessage(exc.getMessage());
		er.setErroeData("Entered Product Id is Not Found In DataBase");
		
		return new ResponseEntity<ErrorStructure<String>>(er,HttpStatus.NOT_FOUND);
	}
}
