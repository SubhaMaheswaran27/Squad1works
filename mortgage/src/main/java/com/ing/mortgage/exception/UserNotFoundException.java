package com.ing.mortgage.exception;

import java.io.Serializable;

public class UserNotFoundException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "No user found in this emailId";

	public UserNotFoundException() {
		super(MESSAGE);
	}

}
