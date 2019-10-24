package com.ing.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.bank.dto.RequestTransferDto;
import com.ing.bank.dto.ResponseTransferDto;
import com.ing.bank.dto.TransactionHistoryDto;
import com.ing.bank.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/user")
@Slf4j
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@PostMapping("/transfer")
	public ResponseEntity<ResponseTransferDto> transfer(@RequestBody RequestTransferDto requestTransferDto) {
		log.info("inside fund transafer");
		ResponseTransferDto responseTransferDto = transactionService.transfer(requestTransferDto);
		return new ResponseEntity<>(responseTransferDto, HttpStatus.OK);
	}

	@GetMapping("/transactionSummary/{userId}")
	public ResponseEntity<List<TransactionHistoryDto>> weekHistory(@PathVariable("userId") Long userId) {
		log.info("inside weekHistory transafer");
		List<TransactionHistoryDto> listTransactionHistoryDto = transactionService.weekHistory(userId);
		return new ResponseEntity<>(listTransactionHistoryDto, HttpStatus.FOUND);
	}

	@GetMapping("/transactionSummary/{userId}/{year}/{monthName}")
	public ResponseEntity<List<TransactionHistoryDto>> monthHistory(@PathVariable("userId") Long userId,
			@PathVariable("year") int year, @PathVariable("monthName") String monthName) {
		log.info("inside month history");
		List<TransactionHistoryDto> listTransactionHistoryDto = transactionService.monthHistory(userId, year,
				monthName);
		return new ResponseEntity<>(listTransactionHistoryDto, HttpStatus.FOUND);
	}

}
