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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.mortgage.dto.RequestUserDto;
import com.ing.mortgage.dto.ResponseLoanDto;
import com.ing.mortgage.dto.ResponseUserDto;
import com.ing.mortgage.service.UserService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@PostMapping("/save")
	public ResponseEntity<ResponseUserDto> register(@RequestBody RequestUserDto requestUserDto) {
		logger.info("inside user save");
		ResponseUserDto responseUserDto = userService.register(requestUserDto);
		return new ResponseEntity<>(responseUserDto, HttpStatus.CREATED);
	}

	@GetMapping("/loans{email}")
	public ResponseEntity<List<ResponseLoanDto>> myLoans(@PathVariable("email") String email) {
		logger.info("inside user loan details");
		List<ResponseLoanDto> responseLoanDto = userService.myLoans(email);
		return new ResponseEntity<>(responseLoanDto, HttpStatus.FOUND);

	}

}
