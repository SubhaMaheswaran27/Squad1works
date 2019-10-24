package com.ing.bank.service;

import java.time.LocalDateTime;
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

import com.ing.bank.dto.RequestTransferDto;
import com.ing.bank.dto.ResponseTransferDto;
import com.ing.bank.dto.TransactionHistoryDto;
import com.ing.bank.entity.Account;
import com.ing.bank.entity.Beneficiary;
import com.ing.bank.entity.Transaction;
import com.ing.bank.repository.AccountRepository;
import com.ing.bank.repository.BeneficiaryRepository;
import com.ing.bank.repository.TransactionRepository;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

	@Mock
	AccountRepository accountRepository;

	@Mock
	TransactionRepository transactionRepository;

	@Mock
	BeneficiaryRepository beneficiaryRepository;

	@InjectMocks
	TransactionServiceImpl transactionServiceImpl;

	Account debitFromAccount;
	Account paidToAccount;
	Transaction transaction;
	RequestTransferDto requestTransferDto;
	TransactionHistoryDto transactionHistoryDto;
	List<TransactionHistoryDto> listTransactionHistoryDto;
	List<Transaction> listTransaction;
	Beneficiary beneficiary;

	@Before
	public void setup() {

		beneficiary = new Beneficiary();
		beneficiary.setBeneficiaryAccountNumber("36429173934033");

		LocalDateTime toDate = LocalDateTime.now();
		debitFromAccount = new Account();
		debitFromAccount.setAccountId(1L);
		debitFromAccount.setAccountNumber("36429173934033");
		debitFromAccount.setAccountType("savings");
		debitFromAccount.setBalance(10000D);
		debitFromAccount.setUserId(1L);

		paidToAccount = new Account();
		paidToAccount.setAccountId(1L);
		paidToAccount.setAccountNumber("72904846165874");
		paidToAccount.setAccountType("savings");
		paidToAccount.setBalance(10000D);
		paidToAccount.setUserId(1L);

		transaction = new Transaction();
		transaction.setTransactionId(1L);
		transaction.setTransferredDate(toDate);
		transaction.setTransactionType("debit");

		requestTransferDto = new RequestTransferDto();
		requestTransferDto.setUserId(1L);
		requestTransferDto.setToAccount("36429173934033");
		requestTransferDto.setTransferredAmount(100);

		transactionHistoryDto = new TransactionHistoryDto();
		transactionHistoryDto.setTransferredId(1L);
		transactionHistoryDto.setTransferredDate(toDate);

		listTransactionHistoryDto = new ArrayList<>();
		listTransactionHistoryDto.add(transactionHistoryDto);

		listTransaction = new ArrayList<>();
		listTransaction.add(transaction);
	}

	@Test
	public void testTransfer() {
		Mockito.when(accountRepository.findByUserId(Mockito.anyLong())).thenReturn(debitFromAccount);
		Mockito.when(accountRepository.findByAccountNumber(Mockito.anyString())).thenReturn(paidToAccount);
		Mockito.when(beneficiaryRepository.findByUserAccountNumberAndBeneficiaryAccountNumber(Mockito.anyString(),
				Mockito.anyString())).thenReturn(beneficiary);
		Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);
		ResponseTransferDto responseTransferDto = transactionServiceImpl.transfer(requestTransferDto);
		Assert.assertEquals(Long.valueOf(1), responseTransferDto.getTransferredId());
	}

	@Test
	public void testWeekHistory() {
		Mockito.when(accountRepository.findByUserId(Mockito.anyLong())).thenReturn(debitFromAccount);
		Mockito.when(transactionRepository.findByAccountAndTransferredDate(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(listTransaction);
		List<TransactionHistoryDto> transactionHistoryDto = transactionServiceImpl.weekHistory(1L);
		Assert.assertEquals(listTransaction.size(), transactionHistoryDto.size());
	}

	@Test
	public void testMonthHistory() {
		Mockito.when(accountRepository.findByUserId(Mockito.anyLong())).thenReturn(debitFromAccount);
		Mockito.when(transactionRepository.findByAccountAndTransferredDateBetween(Mockito.any(), Mockito.any(),
				Mockito.any())).thenReturn(listTransaction);
		List<TransactionHistoryDto> transactionHistoryDto = transactionServiceImpl.monthHistory(1L, 2019, "SEPTEMBER");
		Assert.assertEquals(listTransaction.size(), transactionHistoryDto.size());
	}
}
