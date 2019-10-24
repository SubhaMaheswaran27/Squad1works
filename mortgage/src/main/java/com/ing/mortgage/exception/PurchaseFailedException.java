package com.ing.mortgage.exception;

import java.io.Serializable;

public class PurchaseFailedException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "purchase failed";

	public PurchaseFailedException() {
		super(MESSAGE);

	}
}
