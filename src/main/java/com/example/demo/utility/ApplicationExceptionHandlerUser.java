package com.example.demo.utility;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ApplicationExceptionHandlerUser extends ResponseEntityExceptionHandler {

	private ErrorStructure<Map<String, String>> errorStructure;

	public ApplicationExceptionHandlerUser(ErrorStructure<Map<String, String>> errorStructure) {
		super();
		this.errorStructure = errorStructure;
	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<ObjectError> errors = ex.getAllErrors();

		Map<String,String> message = new HashMap<String,String>();

		errors.forEach(error -> {
			message.put(((FieldError) error).getField(), error.getDefaultMessage());
		});

		return ResponseEntity.badRequest().body(errorStructure.setErrorStatusCode(HttpStatus.BAD_REQUEST.value())
				.setErrorMessage("Invali Input" ).setErroeData(message));






	}
	//		List<ObjectError> errors=ex.getAllErrors();
	//		List<String> messages=new ArrayList<String>();
	//		errors.forEach(error->{
	//			String message=error.getDefaultMessage();
	//			messages.add(message);
	//		});
	//				return ResponseEntity.badRequest().body(
	//						errorStructure.setErrorStatusCode(HttpStatus.BAD_REQUEST.value())
	//						
	//			);



}