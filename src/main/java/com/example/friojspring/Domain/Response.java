package com.example.friojspring.Domain;

import java.io.Serializable;

public class Response implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6492341511620100543L;
	private String message;
	
	
	public Response(String message) {
		super();
		this.message = message;
	}
	
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
