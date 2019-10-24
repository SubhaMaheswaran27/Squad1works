package com.ing.bank.service;

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

import com.ing.bank.dto.ListBeneficiaryDto;
import com.ing.bank.dto.RequestBeneficiaryDto;
import com.ing.bank.dto.ResponseBeneficiaryDto;
import com.ing.bank.entity.Account;
import com.ing.bank.entity.Beneficiary;
import com.ing.bank.repository.AccountRepository;
import com.ing.bank.repository.BeneficiaryRepository;

@RunWith(MockitoJUnitRunner.class)
public class BeneficiaryServiceTest {

	@Mock
	AccountRepository accountRepository;

	@InjectMocks
	BeneficiaryServiceImpl beneficiaryServiceImpl;

	@Mock
	BeneficiaryRepository beneficiaryRepository;

	Account account;

	Account beneficiaryAccount;

	RequestBeneficiaryDto requestBeneficiaryDto;

	Beneficiary beneficiary;

	ListBeneficiaryDto beneficiaryDto;

	List<ListBeneficiaryDto> listBeneficiaryDto;

	List<Beneficiary> listBeneficiary;

	@Before
	public void setup() {
		account = new Account();
		account.setAccountId(1L);
		account.setAccountNumber("36429173934033");
		account.setAccountType("savings");
		account.setBalance(10000D);
		account.setUserId(1L);

		beneficiaryAccount = new Account();

		beneficiaryAccount.setAccountId(1L);
		beneficiaryAccount.setAccountNumber("72904846165874");
		beneficiaryAccount.setAccountType("savings");
		beneficiaryAccount.setBalance(10000D);
		beneficiaryAccount.setUserId(3L);

		requestBeneficiaryDto = new RequestBeneficiaryDto();
		requestBeneficiaryDto.setBeneficiaryAccountNumber(beneficiaryAccount.getAccountNumber());
		requestBeneficiaryDto.setBeneficiaryName("pavi");
		requestBeneficiaryDto.setUserId(1L);

		beneficiary = new Beneficiary();
		beneficiary.setBeneficiaryAccountNumber("72904846165874");
		beneficiary.setBeneficiaryId(1L);
		beneficiary.setBeneficiaryName("pavi");
		beneficiary.setUserAccountNumber("36429173934033");

		listBeneficiary = new ArrayList<>();
		listBeneficiary.add(beneficiary);

	}

	@Test
	public void testAddBeneficiary() {
		Mockito.when(accountRepository.findByUserId(Mockito.anyLong())).thenReturn(account);
		Mockito.when(accountRepository.findByAccountNumber(Mockito.anyString())).thenReturn(beneficiaryAccount);
		Mockito.when(beneficiaryRepository.save(Mockito.any())).thenReturn(beneficiary);
		ResponseBeneficiaryDto responseBeneficiaryDto = beneficiaryServiceImpl.addBeneficiary(requestBeneficiaryDto);
		Assert.assertEquals(Long.valueOf(1), responseBeneficiaryDto.getBeneficiaryId());
	}

	@Test
	public void testViewBeneficiaries() {
		Mockito.when(accountRepository.findByUserId(Mockito.anyLong())).thenReturn(account);
		Mockito.when(beneficiaryRepository.findByUserAccountNumber(Mockito.anyString())).thenReturn(listBeneficiary);
		List<ListBeneficiaryDto> listBeneficiaryDto = beneficiaryServiceImpl.viewBeneficiaries(1L);
		Assert.assertEquals(listBeneficiary.size(), listBeneficiaryDto.size());
	}

}
