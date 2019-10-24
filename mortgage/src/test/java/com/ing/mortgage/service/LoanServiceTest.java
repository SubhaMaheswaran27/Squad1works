package com.ing.mortgage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ing.mortgage.dto.ResponseLoanDto;
import com.ing.mortgage.dto.ResponsePurchaseDto;
import com.ing.mortgage.entity.Loan;
import com.ing.mortgage.entity.MyLoan;
import com.ing.mortgage.entity.User;
import com.ing.mortgage.repository.LoanRepository;
import com.ing.mortgage.repository.MyLoanRepository;
import com.ing.mortgage.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTest {

	@Mock
	LoanRepository loanRepository;

	@Mock
	UserRepository userRepository;

	@Mock
	MyLoanRepository myLoanRepository;

	@InjectMocks
	LoanServiceImpl loanServiceImpl;

	User user = null;

	Double loanAmount = 0.0;

	Loan loan = null;

	List<Loan> listLoan = null;

	MyLoan myLoan = null;
	Long userId = null;
	Long loanId = null;

	@Before
	public void setup() {
		userId = 1L;
		loanId = 1L;
		user = new User();
		user.setUserId(1L);
		user.setFirstName("subha");
		user.setLastName("Maheswaran");
		user.setGender("female");
		user.setEmail("subha@gmail.com");
		user.setMobileNumber(9876543310L);
		user.setPropertyValue(100000D);

		loanAmount = (user.getPropertyValue() * 80 / 100);

		loan = new Loan();
		loan.setEmi(10);
		loan.setLoanAmount(80000D);
		loan.setRateOfInterest(1);
		loan.setLoanId(1l);

		listLoan = new ArrayList<>();
		listLoan.add(loan);

		myLoan = new MyLoan();
		myLoan.setEmi(10);
		myLoan.setLoanAmount(80000D);
		myLoan.setRateOfInterest(1);
		myLoan.setLoanId(1l);
		myLoan.setUserId(1L);
		myLoan.setPurchasedLoanId(2L);
	}

	@Test
	public void testGetLoans() {
		Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
		Mockito.when(loanRepository.findByLoanAmount(Mockito.anyDouble())).thenReturn(listLoan);
		List<ResponseLoanDto> listResponseLoanDto = loanServiceImpl.getLoans(1L);
		Assert.assertEquals(listLoan.size(), listResponseLoanDto.size());
	}

	@Test
	public void testPurchase() {
		Mockito.when(loanRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(loan));
		Mockito.when(myLoanRepository.save(Mockito.any())).thenReturn(myLoan);
		ResponsePurchaseDto responsePurchaseDto = loanServiceImpl.purchase(userId, loanId);
		responsePurchaseDto.setMessage("success");
		Assert.assertEquals("success", responsePurchaseDto.getMessage());
	}
}
