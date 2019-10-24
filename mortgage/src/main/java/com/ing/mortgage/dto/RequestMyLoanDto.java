package com.ing.mortgage.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestMyLoanDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	
}
