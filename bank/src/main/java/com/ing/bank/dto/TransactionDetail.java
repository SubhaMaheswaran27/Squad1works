package com.ing.bank.dto;

import java.time.LocalDate;

public interface TransactionDetail {

	Double getTransferredAmount();

	LocalDate getTransferredDate();

	String getToAccount();

}
