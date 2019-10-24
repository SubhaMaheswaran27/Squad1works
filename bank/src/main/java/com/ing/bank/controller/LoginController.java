package com.ing.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.bank.dto.RequestLoginDto;
import com.ing.bank.dto.ResponseLoginDto;
import com.ing.bank.service.LoginService;

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

public class LoginController {

	@Autowired
	LoginService loginService;

	/*
	 * This method is used to login the user by providing valid credentials
	 * 
	 * @Body userName,password
	 * 
	 * @return ResponseLoginDto is the return object which includes userId,message
	 * 
	 */

	@PostMapping("/login")
	public ResponseEntity<ResponseLoginDto> login(@RequestBody RequestLoginDto requestLoginDto) {
		log.info("inside login ");
		ResponseLoginDto responseLoginDto = loginService.login(requestLoginDto);
		return new ResponseEntity<>(responseLoginDto, HttpStatus.OK);
	}
}
