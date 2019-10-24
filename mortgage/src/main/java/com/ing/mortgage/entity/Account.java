package com.ing.mortgage.entity;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long accountId;
	private String accountNumber;
	private String accountType;
	private Double accountBalance;

}
