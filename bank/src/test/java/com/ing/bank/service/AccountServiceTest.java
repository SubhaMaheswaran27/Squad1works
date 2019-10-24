package com.ing.bank.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ing.bank.dto.ResponseAccountDto;
import com.ing.bank.entity.Account;
import com.ing.bank.repository.AccountRepository;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

	@Mock
	AccountRepository accountRepository;

	@InjectMocks
	AccountServiceImpl accountServiceImpl;

	Account account;

	@Before
	public void setup() {

		account = new Account();
		account.setAccountId(1L);
		account.setAccountNumber("36429173934033");
		account.setAccountType("savings");
		account.setBalance(10000D);
		account.setUserId(1L);
	}

	@Test
	public void testAccountSummary() {
		Mockito.when(accountRepository.findByUserId(Mockito.anyLong())).thenReturn(account);
		ResponseAccountDto responseAccountDto = accountServiceImpl.accountSummary(1L);
		Assert.assertEquals("36429173934033", responseAccountDto.getAccountNumber());
	}
}
