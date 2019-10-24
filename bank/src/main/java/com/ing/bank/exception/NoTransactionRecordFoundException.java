package com.ing.bank.exception;

import java.io.Serializable;

public class NoTransactionRecordFoundException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "No records found";

	public NoTransactionRecordFoundException() {
		super(MESSAGE);
	}

}
