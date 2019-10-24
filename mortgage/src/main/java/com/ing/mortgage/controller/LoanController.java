package com.ing.mortgage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.mortgage.dto.ResponseLoanDto;
import com.ing.mortgage.dto.ResponsePurchaseDto;
import com.ing.mortgage.service.LoanService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("loan")
public class LoanController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	LoanService loanService;

	@GetMapping("/{userId}")
	public ResponseEntity<List<ResponseLoanDto>> getLoans(@PathVariable("userId") Long userId) {
		logger.info("inside loan controller");
		List<ResponseLoanDto> responseLoanDto = loanService.getLoans(userId);
		return new ResponseEntity<>(responseLoanDto, HttpStatus.FOUND);
	}

	@PostMapping("/purchase/{userId}/{loanId}")
	public ResponseEntity<ResponsePurchaseDto> purchase(@PathVariable("userId") Long userId,
			@PathVariable("loanId") Long loanId) {
		logger.info("inside loan purchase controller");
		ResponsePurchaseDto responsePurchaseDto = loanService.purchase(userId, loanId);
		return new ResponseEntity<>(responsePurchaseDto, HttpStatus.OK);
	}
}
