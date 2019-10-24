package com.ing.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.bank.dto.ResponseAccountDto;
import com.ing.bank.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/user")
@Slf4j
/**
 * 
 * @author SubhaMaheswaran
 *
 */
public class AccountController {
	@Autowired
	AccountService accountService;
	
	/*
	 * This method is used to get the account details for the particular user.
	 * 
	 * @Param userId
	 * 
	 * @return ResponseAccountDto is the return object which includes
	 * accountNumber,accountType,balance,createdDate
	 * 
	 */
	@GetMapping("summary/{userId}")
	public ResponseEntity<ResponseAccountDto> accountSummary(@PathVariable("userId") Long userId) {
		log.info("inside accountSummary");
		ResponseAccountDto responseAccountDto = accountService.accountSummary(userId);
		return new ResponseEntity<>(responseAccountDto, HttpStatus.FOUND);

	}

}
