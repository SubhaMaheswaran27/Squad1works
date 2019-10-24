package com.ing.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ing.bank.dto.RequestTransferDto;
import com.ing.bank.dto.ResponseTransferDto;
import com.ing.bank.dto.TransactionDetail;
import com.ing.bank.dto.TransactionHistoryDto;

@Service
public interface TransactionService {

	ResponseTransferDto transfer(RequestTransferDto requestTransferDto);

	List<TransactionHistoryDto> transactionHistory(Long accountId);

	List<TransactionHistoryDto> weekHistory(Long userId);

	List<TransactionDetail> findByTransferredAmount(int weeks);

	List<TransactionHistoryDto> monthHistory(Long userId, int year, String monthName);

}
