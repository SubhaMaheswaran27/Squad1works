package com.ing.mortgage.exception;

import java.io.Serializable;

public class InvalidMobileNumberException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "Please enter a valid mobile  Number";

	public InvalidMobileNumberException() {
		super(MESSAGE);
	}

}
