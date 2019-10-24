package com.ing.stock.exception;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;
	private int statusCode;

	public ErrorResponse(String message, int statusCode) {
		this.message = message;
		this.statusCode = statusCode;

	}

}
