package com.ing.mortgage.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseLoanDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long loanId;
	private Double loanAmount;
	private float rateOfInterest;
	private int emi;
	
}
