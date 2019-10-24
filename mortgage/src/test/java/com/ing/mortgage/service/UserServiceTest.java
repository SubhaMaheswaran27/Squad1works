package com.ing.mortgage.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ing.mortgage.dto.RequestUserDto;
import com.ing.mortgage.dto.ResponseLoanDto;
import com.ing.mortgage.dto.ResponseUserDto;
import com.ing.mortgage.entity.MyLoan;
import com.ing.mortgage.entity.User;
import com.ing.mortgage.repository.MyLoanRepository;
import com.ing.mortgage.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
	@Mock
	UserRepository userRepository;

	@Mock
	MyLoanRepository myLoanRepository;

	@InjectMocks
	UserServiceImpl userServiceImpl;

	User user = null;
	RequestUserDto requestUserDto = null;
	List<User> listUser = null;

	List<MyLoan> listMyLoan = null;

	MyLoan myLoan = null;

	List<ResponseLoanDto> responseLoanDto = null;

	@Before
	public void setup() {
		user = new User();
		user.setUserId(1L);
		user.setFirstName("subha");
		user.setLastName("Maheswaran");
		user.setGender("female");
		user.setEmail("subha@gmail.com");
		user.setMobileNumber(9876543310L);
		listUser = new ArrayList<User>();
		listUser.add(user);
		requestUserDto = new RequestUserDto();
		requestUserDto.setFirstName("subha");
		requestUserDto.setEmail("jeeva@gmail.com");
		requestUserDto.setLastName("Maheswaran");
		requestUserDto.setGender("female");
		requestUserDto.setMobileNumber(9876543310L);
		requestUserDto.setDateOfBirth("1997-01-01");

		myLoan = new MyLoan();
		myLoan.setEmi(10);
		myLoan.setLoanAmount(80000D);
		myLoan.setRateOfInterest(1f);
		myLoan.setUserId(1L);
		myLoan.setLoanId(1L);

		listMyLoan = new ArrayList<MyLoan>();
		listMyLoan.add(myLoan);

		responseLoanDto = new ArrayList<ResponseLoanDto>();
	}

	@Test
	public void testRegister() {
		Mockito.when(userRepository.findAll()).thenReturn(listUser);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		ResponseUserDto responseUserDto = userServiceImpl.register(requestUserDto);
		//logger.info("responseuserdto : {} ", responseUserDto.getFirstName());
		responseUserDto.setMessaage("success");
		Assert.assertEquals("success", responseUserDto.getMessaage());

	}

	@Test
	public void testMyLoans() {
		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user);
		Mockito.when(myLoanRepository.findByUserId(Mockito.anyLong())).thenReturn(listMyLoan);
		responseLoanDto = userServiceImpl.myLoans("subha@gmail.com");
		Assert.assertEquals(listMyLoan.size(), responseLoanDto.size());

	}
}
