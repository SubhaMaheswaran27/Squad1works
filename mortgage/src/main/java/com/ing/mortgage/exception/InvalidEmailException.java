package com.ing.mortgage.exception;

import java.io.Serializable;

public class InvalidEmailException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "Please enter a valid email..";

	public InvalidEmailException() {
		super(MESSAGE);
	}

}
