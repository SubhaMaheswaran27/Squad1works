package com.ing.bank.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResponseAccountDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String accountNumber;
	private String accountType;
	private Double balance;
	private LocalDate createdDate;

}
