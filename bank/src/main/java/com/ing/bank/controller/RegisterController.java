package com.ing.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.bank.dto.RegisterRequestDto;
import com.ing.bank.dto.RegisterResponseDto;
import com.ing.bank.service.RegisterService;

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
public class RegisterController {

	@Autowired
	RegisterService registerService;

	/*
	 * This method is used to register the user by providing valid details, while
	 * registering it will generate password for the user. Once user registered
	 * successfully it will generate account for the user.
	 * 
	 * @Body userName, gender,dateOfBirth, mobileNumber,email
	 * 
	 * @return RegisterResponseDto is the return object which includes
	 * userId,password,accountNumber,message
	 * 
	 */
	@PostMapping("/register")
	public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto) {
		log.info("inside register controller");
		RegisterResponseDto registerResponseDto = registerService.register(registerRequestDto);
		return new ResponseEntity<>(registerResponseDto, HttpStatus.CREATED);
	}

}
