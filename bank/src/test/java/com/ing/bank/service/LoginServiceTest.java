package com.ing.bank.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ing.bank.dto.RequestLoginDto;
import com.ing.bank.dto.ResponseLoginDto;
import com.ing.bank.entity.Register;
import com.ing.bank.repository.RegisterRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

	@Mock
	RegisterRepository registerRepository;

	@InjectMocks
	LoginServiceImpl loginServiceImpl;

	RequestLoginDto requestLoginDto;

	Register register;

	@Before
	public void setup() {
		requestLoginDto = new RequestLoginDto();
		requestLoginDto.setEmail("subha@gmail.com");
		requestLoginDto.setPassword("subha");
		register = new Register();
		register.setUserId(1L);
		register.setGender("female");
	}

	@Test
	public void testLogin() {
		Mockito.when(registerRepository.findByEmailAndPassword(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(register);
		ResponseLoginDto responseLoginDto = loginServiceImpl.login(requestLoginDto);
		Assert.assertEquals(Long.valueOf(1L), responseLoginDto.getUserId());
	}

}
