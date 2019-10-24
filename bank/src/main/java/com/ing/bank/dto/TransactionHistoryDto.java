package com.ing.bank.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionHistoryDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long transferredId;
	private String accountNumber;
	private String transactionType;
	private double transferredAmount;
	private LocalDateTime transferredDate;
}
