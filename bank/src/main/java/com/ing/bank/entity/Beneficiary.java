package com.ing.bank.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Beneficiary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long beneficiaryId;
	private String userAccountNumber;
	private String beneficiaryAccountNumber;
	private String beneficiaryName;
	private LocalDate beneficiaryAddedDate;

}
