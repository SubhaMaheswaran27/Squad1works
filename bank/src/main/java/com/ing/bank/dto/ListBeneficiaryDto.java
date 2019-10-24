package com.ing.bank.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ListBeneficiaryDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long beneficiaryId;
	private String beneficiaryAccountNumber;
	private String beneficiaryName;
	private LocalDate beneficiaryAddedDate;

}
