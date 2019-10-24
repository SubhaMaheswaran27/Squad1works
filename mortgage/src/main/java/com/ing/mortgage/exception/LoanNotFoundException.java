package com.ing.mortgage.exception;

import java.io.Serializable;

public class LoanNotFoundException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "No loan found";

	public LoanNotFoundException() {
		super(MESSAGE);
	}

}
