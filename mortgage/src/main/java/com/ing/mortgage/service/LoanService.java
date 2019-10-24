package com.ing.mortgage.service;

import java.util.List;

import com.ing.mortgage.dto.ResponseLoanDto;
import com.ing.mortgage.dto.ResponsePurchaseDto;

public interface LoanService {

	List<ResponseLoanDto> getLoans(Long userId);

	ResponsePurchaseDto purchase(Long userId, Long loanId);

}
