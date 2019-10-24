package com.ing.mortgage.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.mortgage.dto.ResponseLoanDto;
import com.ing.mortgage.dto.ResponsePurchaseDto;
import com.ing.mortgage.entity.Loan;
import com.ing.mortgage.entity.MyLoan;
import com.ing.mortgage.entity.User;
import com.ing.mortgage.exception.LoanNotFoundException;
import com.ing.mortgage.exception.UserNotFoundException;
import com.ing.mortgage.repository.LoanRepository;
import com.ing.mortgage.repository.MyLoanRepository;
import com.ing.mortgage.repository.UserRepository;

@Service
public class LoanServiceImpl implements LoanService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private MyLoanRepository myLoanRepository;

	public List<ResponseLoanDto> getLoans(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent())
			throw new UserNotFoundException();

		Double loanAmount = (user.get().getPropertyValue() * 80 / 100);
		List<Loan> loans = loanRepository.findByLoanAmount(loanAmount);
		List<ResponseLoanDto> listResponseLoanDto = new ArrayList<>();
		loans.stream().forEach(l -> {
			ResponseLoanDto responseLoanDto = new ResponseLoanDto();
			responseLoanDto.setLoanId(l.getLoanId());
			responseLoanDto.setLoanAmount(l.getLoanAmount());
			responseLoanDto.setEmi(l.getEmi());
			responseLoanDto.setRateOfInterest(l.getRateOfInterest());
			listResponseLoanDto.add(responseLoanDto);
		});
		if (listResponseLoanDto.isEmpty())
			throw new NullPointerException();
		return listResponseLoanDto;
	}

	@Override
	public ResponsePurchaseDto purchase(Long userId, Long loanId) {
		Optional<Loan> loan = loanRepository.findById(loanId);
		if (!loan.isPresent())
			throw new LoanNotFoundException();
		MyLoan myLoan = new MyLoan();
		myLoan.setLoanAmount(loan.get().getLoanAmount());
		myLoan.setEmi(loan.get().getEmi());
		myLoan.setRateOfInterest(loan.get().getRateOfInterest());
		myLoan.setUserId(userId);
		myLoan.setLoanId(loan.get().getLoanId());
		MyLoan response = myLoanRepository.save(myLoan);

		ResponsePurchaseDto responsePurchaseDto = new ResponsePurchaseDto();
		responsePurchaseDto.setPurchaseId(response.getPurchasedLoanId());
		responsePurchaseDto.setMessage("Loan purchased successfully..");
		return responsePurchaseDto;

	}

}
