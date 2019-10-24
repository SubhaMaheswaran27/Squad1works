package com.ing.bank.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.bank.dto.ResponseAccountDto;
import com.ing.bank.entity.Account;
import com.ing.bank.exception.CommonException;
import com.ing.bank.repository.AccountRepository;
import com.ing.bank.util.ExceptionConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
/**
 * 
 * @author SubhaMaheswaran
 *
 */
public class AccountServiceImpl implements AccountService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	transient AccountRepository accountRepository;

	/*
	 * This method is used to get the account details for the particular user.
	 * 
	 * @Param userId
	 * 
	 * @return ResponseAccountDto is the return object which includes
	 * accountNumber,accountType,balance,createdDate
	 * 
	 */

	@Override
	public ResponseAccountDto accountSummary(Long userId) {
		log.info("inside accountSummary service");
		Account account = accountRepository.findByUserId(userId);
		if (account == null) {
			throw new CommonException(ExceptionConstants.ACCOUNT_NOT_FOUND);
		}
		ResponseAccountDto responseAccountDto = new ResponseAccountDto();
		responseAccountDto.setAccountNumber(account.getAccountNumber());
		responseAccountDto.setAccountType(account.getAccountType());
		responseAccountDto.setBalance(account.getBalance());
		responseAccountDto.setCreatedDate(account.getCreatedDate());
		return responseAccountDto;
	}

}
