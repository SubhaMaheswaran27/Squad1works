package com.ing.stock.exception;

import java.io.Serializable;

public class StockIdNotFoundException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "This stock id not exist";

	public StockIdNotFoundException() {
		super(MESSAGE);
	}
}