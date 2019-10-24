package com.ing.bank.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.bank.dto.ListBeneficiaryDto;
import com.ing.bank.dto.RequestBeneficiaryDto;
import com.ing.bank.dto.ResponseBeneficiaryDto;
import com.ing.bank.entity.Account;
import com.ing.bank.entity.Beneficiary;
import com.ing.bank.exception.CommonException;
import com.ing.bank.repository.AccountRepository;
import com.ing.bank.repository.BeneficiaryRepository;
import com.ing.bank.util.ExceptionConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
/**
 * 
 * @author SubhaMaheswaran
 *
 */
public class BeneficiaryServiceImpl implements BeneficiaryService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	transient BeneficiaryRepository beneficiaryRepository;

	@Autowired
	transient AccountRepository accountRepository;

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
	@Override
	public ResponseBeneficiaryDto addBeneficiary(RequestBeneficiaryDto requestBeneficiaryDto) {
		log.info("inside add beneficiary Service");
		Account userAccount = accountRepository.findByUserId(requestBeneficiaryDto.getUserId());
		Account beneficiaryAccount = accountRepository
				.findByAccountNumber(requestBeneficiaryDto.getBeneficiaryAccountNumber());

		if (userAccount.getAccountNumber().equalsIgnoreCase(requestBeneficiaryDto.getBeneficiaryAccountNumber())) {
			throw new CommonException(ExceptionConstants.INVALID_BENEFICIARY_ACCOUNT);
		}

		if (beneficiaryAccount == null) {
			throw new CommonException(ExceptionConstants.BENEFICIARY_NOT_FOUND);
		}

		Beneficiary existBeneficiary = beneficiaryRepository.findByUserAccountNumberAndBeneficiaryAccountNumber(
				userAccount.getAccountNumber(), beneficiaryAccount.getAccountNumber());

		if (existBeneficiary != null) {
			throw new CommonException(ExceptionConstants.DUPLICATE_BENEFICIARY);
		}
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setBeneficiaryAccountNumber(requestBeneficiaryDto.getBeneficiaryAccountNumber());
		beneficiary.setUserAccountNumber(userAccount.getAccountNumber());
		beneficiary.setBeneficiaryName(requestBeneficiaryDto.getBeneficiaryName());
		beneficiary.setBeneficiaryAddedDate(LocalDate.now());
		Beneficiary response = beneficiaryRepository.save(beneficiary);
		if (response.getBeneficiaryId() == null) {
			throw new CommonException(ExceptionConstants.ADD_BENEFICIARY_FAILED);
		}
		ResponseBeneficiaryDto responseBeneficiaryDto = new ResponseBeneficiaryDto();
		responseBeneficiaryDto.setBeneficiaryId(response.getBeneficiaryId());
		responseBeneficiaryDto.setMessage("Beneficiary added successfully");
		return responseBeneficiaryDto;
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
	@Override
	public List<ListBeneficiaryDto> viewBeneficiaries(Long userId) {
		List<ListBeneficiaryDto> beneficiaryDtoList = new ArrayList<>();
		Account userAccount = accountRepository.findByUserId(userId);
		List<Beneficiary> listBeneficiary = beneficiaryRepository
				.findByUserAccountNumber(userAccount.getAccountNumber());

		listBeneficiary.stream().forEach(b -> {
			ListBeneficiaryDto listBeneficiaryDto = new ListBeneficiaryDto();
			listBeneficiaryDto.setBeneficiaryAccountNumber(b.getBeneficiaryAccountNumber());
			listBeneficiaryDto.setBeneficiaryAddedDate(b.getBeneficiaryAddedDate());
			listBeneficiaryDto.setBeneficiaryId(b.getBeneficiaryId());
			listBeneficiaryDto.setBeneficiaryName(b.getBeneficiaryName());
			beneficiaryDtoList.add(listBeneficiaryDto);
		});
		if (beneficiaryDtoList.isEmpty()) {
			throw new CommonException(ExceptionConstants.NO_BENEFICIARY_ADDED);
		}
		return beneficiaryDtoList;
	}

}
