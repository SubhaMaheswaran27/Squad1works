package com.ing.mortgage.exception;

import java.io.Serializable;

public class InvalidLastName extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "your last is invalid,enter alphabets only";

	public InvalidLastName() {
		super(MESSAGE);
	}

}
