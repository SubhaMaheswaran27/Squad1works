package com.ing.bank.controller;

import java.util.List;

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

import com.ing.bank.dto.ListBeneficiaryDto;
import com.ing.bank.dto.RequestBeneficiaryDto;
import com.ing.bank.dto.ResponseBeneficiaryDto;
import com.ing.bank.service.BeneficiaryService;

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
public class BeneficiaryController {

	@Autowired
	BeneficiaryService beneficiaryService;

	/*
	 * This method is used for the user can add the beneficiary by providing valid
	 * details.
	 * 
	 * @Body requestBeneficiaryDto is the input object which includes userId,
	 * beneficiaryAccountNumber,beneficiaryName
	 * 
	 * @return ResponseBeneficiaryDto is the return object which includes
	 * beneficiaryId,message
	 * 
	 */
	@PostMapping("/beneficiary")
	public ResponseEntity<ResponseBeneficiaryDto> addBeneficiary(
			@RequestBody RequestBeneficiaryDto requestBeneficiaryDto) {
		log.info("inside add beneficiary");
		ResponseBeneficiaryDto responseBeneficiaryDto = beneficiaryService.addBeneficiary(requestBeneficiaryDto);
		return new ResponseEntity<>(responseBeneficiaryDto, HttpStatus.CREATED);
	}

	/*
	 * This method is used for the user can view their beneficiaries
	 * 
	 * @Param userId
	 * 
	 * @return List<ListBeneficiaryDto> is the return object which includes
	 * beneficiaryAccountNumber,beneficiaryAddedDate,beneficiaryId,beneficiaryName
	 * 
	 */
	@GetMapping("/beneficiary/{userId}")
	public ResponseEntity<List<ListBeneficiaryDto>> viewBeneficiaries(@PathVariable("userId") Long userId) {
		log.info("inside view beneficiary");
		List<ListBeneficiaryDto> listBeneficiaryDto = beneficiaryService.viewBeneficiaries(userId);
		return new ResponseEntity<>(listBeneficiaryDto, HttpStatus.FOUND);
	}
}
