package com.ing.bank.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ing.bank.dto.RegisterRequestDto;
import com.ing.bank.dto.RegisterResponseDto;
import com.ing.bank.entity.Account;
import com.ing.bank.entity.Register;
import com.ing.bank.repository.AccountRepository;
import com.ing.bank.repository.RegisterRepository;

@RunWith(MockitoJUnitRunner.class)
public class RegisterServiceTest {

	@Mock
	RegisterRepository registerRepository;

	@Mock
	AccountRepository accountRepository;

	@InjectMocks
	RegisterServiceImpl registerServiceImpl;
	
	RegisterRequestDto registerRequestDto;

	Register register;

	Account account;

	@Before
	public void setup() {
		registerRequestDto = new RegisterRequestDto();
		registerRequestDto.setUserName("subha");
		registerRequestDto.setMobileNumber(9988776655L);
		registerRequestDto.setGender("female");
		registerRequestDto.setEmail("subha@gmail.com");
		registerRequestDto.setDateOfBirth("1997-01-01");

		register = new Register();
		register.setUserId(1l);
		register.setUserName("subha");
		register.setAge(18);
		register.setGender("female");
		register.setMobileNumber(9988776655L);
		register.setPassword("KoMcw");

		account = new Account();
		account.setAccountId(1L);
		account.setAccountNumber("36429173934033");
		account.setAccountType("savings");
		account.setBalance(10000D);
		account.setUserId(1L);
	}

	@Test
	public void testRegister() {
		Mockito.when(registerRepository.findByEmail(Mockito.anyString())).thenReturn(null);
		Mockito.when(registerRepository.save(Mockito.any())).thenReturn(register);
		Mockito.when(accountRepository.save(Mockito.any())).thenReturn(account);
		RegisterResponseDto registerResponseDto = registerServiceImpl.register(registerRequestDto);
		Assert.assertEquals("36429173934033", registerResponseDto.getAccountNumber());

	}
}
