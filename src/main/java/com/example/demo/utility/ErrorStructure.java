package com.example.demo.utility;

import org.springframework.stereotype.Component;

@Component

public class ErrorStructure<D> {

	private int errorStatusCode;
	private String errorMessage;
	private D erroeData;
	
	public int getErrorStatusCode() {
		return errorStatusCode;
	}
	public ErrorStructure<D> setErrorStatusCode(int errorStatusCode) {
		this.errorStatusCode = errorStatusCode;
		return this;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public ErrorStructure<D> setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}
	public D getErroeData() {
		return erroeData;
	}
	public ErrorStructure<D> setErroeData(D erroeData) {
		this.erroeData = erroeData;
		return this;
		
	}
	
	
}
