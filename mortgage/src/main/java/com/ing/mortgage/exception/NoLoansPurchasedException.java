package com.ing.mortgage.exception;

import java.io.Serializable;

public class NoLoansPurchasedException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "No loans purchased in this emailId";

	public NoLoansPurchasedException(String email) {
		super(MESSAGE +" "+ email);
	}

}
