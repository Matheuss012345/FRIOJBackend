package com.example.friojspring.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailTakenException extends Exception {
	public EmailTakenException() {
		super("This email is already used.");
	}
}
