package com.ing.mortgage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@ExceptionHandler(value = { InvalidMobileNumberException.class })
	public ResponseEntity<ResponseError> phoneException(Exception e) {
		ResponseError error = new ResponseError(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = { InvalidFirstName.class })
	public ResponseEntity<ResponseError> invalidFirstName(Exception e) {
		ResponseError error = new ResponseError(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = { InvalidLastName.class })
	public ResponseEntity<ResponseError> invalidLastName(Exception e) {
		ResponseError error = new ResponseError(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = { InvalidEmailException.class })
	public ResponseEntity<ResponseError> invalidEmailException(Exception e) {
		ResponseError error = new ResponseError(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = { UserNotFoundException.class })
	public ResponseEntity<ResponseError> userNotFoundException(Exception e) {
		ResponseError error = new ResponseError(e.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = { NoLoansPurchasedException.class })
	public ResponseEntity<ResponseError> loansNotFoundException(Exception e) {
		ResponseError error = new ResponseError(e.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler(value = { ExistEmailException.class })
	public ResponseEntity<ResponseError> emailException(Exception e) {
		ResponseError error = new ResponseError(e.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

}
