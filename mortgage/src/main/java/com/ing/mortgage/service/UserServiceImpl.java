package com.ing.mortgage.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.mortgage.dto.RequestUserDto;
import com.ing.mortgage.dto.ResponseLoanDto;
import com.ing.mortgage.dto.ResponseUserDto;
import com.ing.mortgage.entity.Account;
import com.ing.mortgage.entity.MyLoan;
import com.ing.mortgage.entity.User;
import com.ing.mortgage.exception.InvalidEmailException;
import com.ing.mortgage.exception.InvalidFirstName;
import com.ing.mortgage.exception.InvalidLastName;
import com.ing.mortgage.exception.InvalidMobileNumberException;
import com.ing.mortgage.exception.NoLoansPurchasedException;
import com.ing.mortgage.exception.UserNotFoundException;
import com.ing.mortgage.repository.AccountRepository;
import com.ing.mortgage.repository.MyLoanRepository;
import com.ing.mortgage.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	UserRepository userRepository;

	@Autowired
	MyLoanRepository myLoanRepository;

	@Autowired
	AccountRepository accountRepository;

	int count = 0;

	public ResponseUserDto register(RequestUserDto requestUserDto) {

		if (!validEmail(requestUserDto.getEmail()))
			throw new InvalidEmailException();

		if (!validMobileNumber(requestUserDto.getMobileNumber()))
			throw new InvalidMobileNumberException();

		if (!validateFirstName(requestUserDto.getFirstName())) {
			throw new InvalidFirstName();
		}

		if (!validateLastName(requestUserDto.getLastName())) {
			throw new InvalidLastName();
		}

		User userForEmailCheck = userRepository.findByEmail(requestUserDto.getEmail());

		if (userForEmailCheck == null)
			throw new NullPointerException();

		User user = new User();
		user.setFirstName(requestUserDto.getFirstName());
		user.setLastName(requestUserDto.getLastName());
		user.setGender(requestUserDto.getGender());
		user.setMobileNumber(requestUserDto.getMobileNumber());
		user.setEmail(requestUserDto.getEmail());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String birthDay = requestUserDto.getDateOfBirth();
		LocalDate dob = LocalDate.parse(birthDay, formatter);

		user.setDateOfBirth(dob);
		user.setPropertyName(requestUserDto.getPropertyName());
		user.setPropertyType(requestUserDto.getPropertyType());
		user.setPropertyAddress(requestUserDto.getPropertyAddress());
		user.setPropertyValue(requestUserDto.getPropertyValue());
		User responseUser = userRepository.save(user);
		if (responseUser == null)
			throw new NullPointerException();
		Account account = new Account();
		account.setAccountNumber(accountNumber());
		account.setAccountType("savings");
		account.setAccountBalance(1000D);
		Account userAccount = accountRepository.save(account);
		if (userAccount == null)
			throw new NullPointerException();
		ResponseUserDto responseUserDto = new ResponseUserDto();
		responseUserDto.setMessaage("Registration Successfull");
		responseUserDto.setUserId(responseUser.getUserId());
		responseUserDto.setAccountNumber(userAccount.getAccountNumber());
		return responseUserDto;
	}

	@Override
	public List<ResponseLoanDto> myLoans(String email) {
		User user = userRepository.findByEmail(email);

		if (user == null)
			throw new UserNotFoundException();

		List<MyLoan> myLoan = myLoanRepository.findByUserId(user.getUserId());
		List<ResponseLoanDto> listRresponseLoanDto = new ArrayList<>();
		myLoan.stream().forEach(loans -> {
			ResponseLoanDto responseLoanDto = new ResponseLoanDto();
			responseLoanDto.setLoanId(loans.getLoanId());
			responseLoanDto.setLoanAmount(loans.getLoanAmount());
			responseLoanDto.setEmi(loans.getEmi());
			responseLoanDto.setRateOfInterest(loans.getRateOfInterest());
			listRresponseLoanDto.add(responseLoanDto);
		});
		if (listRresponseLoanDto.isEmpty())
			throw new NoLoansPurchasedException(email);
		return listRresponseLoanDto;
	}

	public String accountNumber() {
		Random rand = new Random();
		String number = "";
		for (int i = 0; i < 14; i++) {
			int n = rand.nextInt(10) + 0;
			number += Integer.toString(n);
		}
		return number;

	}

	public boolean validMobileNumber(Long number) {
		String num = number.toString();
		Pattern p = Pattern.compile("^[0-9]{10}$");
		Matcher m = p.matcher(num);
		return (m.find() && m.group().equals(num));

	}

	public boolean validEmail(String email) {
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(email);
		return (m.find() && m.group().equals(email));
	}

	private boolean validateFirstName(String firstName) {
		String name = ("^[a-zA-Z]*$");
		return firstName.matches(name);
	}

	private boolean validateLastName(String middleName) {
		String name = ("^[a-zA-Z]*$");
		return middleName.matches(name);
	}

}
