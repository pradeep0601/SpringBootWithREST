package com.pradeep.springboot.rest.util;

public class RestCustomError {

	private String message;

	public RestCustomError(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
