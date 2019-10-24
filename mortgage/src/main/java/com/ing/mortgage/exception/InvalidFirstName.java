package com.ing.mortgage.exception;

import java.io.Serializable;

public class InvalidFirstName extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "your firstname is invalid,enter alphabets only";

	public InvalidFirstName() {
		super(MESSAGE);
	}

}