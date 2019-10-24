package com.ing.bank.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ing.bank.dto.RequestTransferDto;
import com.ing.bank.dto.ResponseTransferDto;
import com.ing.bank.dto.TransactionDetail;
import com.ing.bank.dto.TransactionHistoryDto;
import com.ing.bank.entity.Account;
import com.ing.bank.entity.Beneficiary;
import com.ing.bank.entity.Transaction;
import com.ing.bank.exception.CommonException;
import com.ing.bank.exception.NoTransactionRecordFoundException;
import com.ing.bank.repository.AccountRepository;
import com.ing.bank.repository.BeneficiaryRepository;
import com.ing.bank.repository.TransactionRepository;
import com.ing.bank.util.ExceptionConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
/**
 * 
 * @author SubhaMaheswaran
 *
 */
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	BeneficiaryRepository beneficiaryRepository;

	/*
	 * This method is used for the user can transfer amount to other users
	 * 
	 * @Body requestTransferDto is the input which includes
	 * debitFrom,paidTo,transferredAmount
	 * 
	 * @return ResponseTransferDto is the return object which includes
	 * transactionId,message
	 * 
	 */
	public ResponseTransferDto transfer(RequestTransferDto requestTransferDto) {
		log.info("inside transaction service");

		ResponseTransferDto responseTransferDto = new ResponseTransferDto();

		Transaction debitTransaction = new Transaction();
		Transaction creditTransaction = new Transaction();

		Account fromAccount = accountRepository.findByUserId(requestTransferDto.getUserId());
		Account toAccount = accountRepository.findByAccountNumber(requestTransferDto.getToAccount());

		if (fromAccount == null) {
			throw new CommonException(ExceptionConstants.ACCOUNT_NOT_FOUND);
		}
		if (toAccount == null) {
			throw new CommonException(ExceptionConstants.ACCOUNT_NOT_FOUND);
		}

		if (fromAccount.getAccountNumber().equalsIgnoreCase(toAccount.getAccountNumber())) {
			throw new CommonException(ExceptionConstants.ACCOUNT_SHOULD_NOT_SAME);
		}

		Beneficiary existBeneficiary = beneficiaryRepository.findByUserAccountNumberAndBeneficiaryAccountNumber(
				fromAccount.getAccountNumber(), toAccount.getAccountNumber());

		if (existBeneficiary == null) {
			throw new CommonException(ExceptionConstants.NOT_A_BENEFICIARY);
		}

		if (fromAccount.getBalance() < requestTransferDto.getTransferredAmount()
				&& requestTransferDto.getTransferredAmount() > 0) {
			throw new CommonException(ExceptionConstants.INVALID_AMOUNT);
		}
		if (requestTransferDto.getTransferredAmount() <= 0) {
			throw new CommonException(ExceptionConstants.INVALID_AMOUNT);
		}
		debitTransaction.setAccountNumber(fromAccount.getAccountNumber());
		debitTransaction.setTransactionType("debit");
		debitTransaction.setTransferredAmount(requestTransferDto.getTransferredAmount());
		debitTransaction.setTransferredDate(LocalDateTime.now());
		debitTransaction.setAccount(fromAccount);

		creditTransaction.setAccountNumber(toAccount.getAccountNumber());
		creditTransaction.setTransactionType("credit");
		creditTransaction.setTransferredAmount(requestTransferDto.getTransferredAmount());
		creditTransaction.setTransferredDate(LocalDateTime.now());
		creditTransaction.setAccount(toAccount);

		double remainingBalance = fromAccount.getBalance() - requestTransferDto.getTransferredAmount();
		double updatedBalance = toAccount.getBalance() + requestTransferDto.getTransferredAmount();

		fromAccount.setBalance(remainingBalance);
		toAccount.setBalance(updatedBalance);

		accountRepository.save(fromAccount);

		Transaction transaction = transactionRepository.save(debitTransaction);
		if (transaction.getTransactionId() == null) {
			throw new CommonException(ExceptionConstants.TRANSACTION_FAILED);
		}
		accountRepository.save(toAccount);
		transactionRepository.save(creditTransaction);

		responseTransferDto.setTransferredId(transaction.getTransactionId());
		responseTransferDto.setMessage("Amount Transferred successfully..");
		return responseTransferDto;

	}

	/*
	 * This method is used get transaction history for particular month
	 * 
	 * @Param userId,year,monthNameis the input
	 * 
	 * @return List<TransactionHistoryDto> is the return object which includes
	 * debitFrom,paidTo,transferredDate,transferredAmount,transactionId
	 * 
	 */

	@Override
	public List<TransactionHistoryDto> monthHistory(Long userId, int year, String monthName) {
		log.info("inside month history");
		Account account = accountRepository.findByUserId(userId);

		List<TransactionHistoryDto> monthHistoryResponse = new ArrayList<>();
		Month mon = Month.valueOf(monthName);
		int month = mon.maxLength();
		LocalDateTime toDate = LocalDateTime.of(year, mon, month, 23, 1);
		LocalDateTime fromDate = LocalDateTime.of(year, mon, 1, 1, 1);

		List<Transaction> listTransaction = transactionRepository.findByAccountAndTransferredDateBetween(account,
				fromDate, toDate);
		listTransaction.stream().forEach(transaction -> {
			TransactionHistoryDto transactionHistoryDto = new TransactionHistoryDto();
			transactionHistoryDto.setTransferredId(transaction.getTransactionId());
			transactionHistoryDto.setAccountNumber(transaction.getAccountNumber());
			transactionHistoryDto.setTransactionType(transaction.getTransactionType());
			transactionHistoryDto.setTransferredAmount(transaction.getTransferredAmount());
			transactionHistoryDto.setTransferredDate(transaction.getTransferredDate());
			monthHistoryResponse.add(transactionHistoryDto);
		});
		if (monthHistoryResponse.isEmpty()) {
			throw new CommonException(ExceptionConstants.MONTH_HISTORY_NOT_FOUND);
		}
		return monthHistoryResponse;

	}

	/*
	 * This method is used get transaction history for last week
	 * 
	 * @Param userId
	 * 
	 * @return List<TransactionHistoryDto> is the return object which includes
	 * transactionType,debitFrom,paidTo,transferredDate,transferredAmount,
	 * transactionId
	 * 
	 */
	@Override
	public List<TransactionHistoryDto> weekHistory(Long userId) {
		log.info("inside week history");
		Account account = accountRepository.findByUserId(userId);
		Long accountId = account.getAccountId();
		LocalDateTime fromDate = LocalDateTime.now().minusDays(7);
		LocalDateTime toDate = LocalDateTime.now();

		List<TransactionHistoryDto> weekHistoryResponse = new ArrayList<>();

		List<Transaction> listTransaction = transactionRepository.findByAccountAndTransferredDate(accountId, fromDate,
				toDate);
		listTransaction.stream().forEach(transaction -> {
			TransactionHistoryDto transactionHistoryDto = new TransactionHistoryDto();
			transactionHistoryDto.setTransferredId(transaction.getTransactionId());
			transactionHistoryDto.setAccountNumber(transaction.getAccountNumber());
			transactionHistoryDto.setTransactionType(transaction.getTransactionType());
			transactionHistoryDto.setTransferredAmount(transaction.getTransferredAmount());
			transactionHistoryDto.setTransferredDate(transaction.getTransferredDate());
			weekHistoryResponse.add(transactionHistoryDto);
		});
		if (weekHistoryResponse.isEmpty()) {
			throw new CommonException(ExceptionConstants.WEEK_HISTORY_NOT_FOUND);
		}
		return weekHistoryResponse;
	}

	public List<TransactionHistoryDto> transactionHistory(Long accountId) {

		log.info("inside month history");
		List<TransactionHistoryDto> listTransactionHistoryDto = new ArrayList<>();
		Pageable pageable = PageRequest.of(0, 10);
		List<Transaction> transaction = transactionRepository.findByAccount(accountId, pageable);
		if (transaction != null) {
			transaction.stream().forEach(a -> {
				TransactionHistoryDto transactionHistoryDto = new TransactionHistoryDto();
				transactionHistoryDto.setTransferredId(a.getTransactionId());
				transactionHistoryDto.setAccountNumber(a.getAccountNumber());
				transactionHistoryDto.setTransactionType(a.getTransactionType());
				transactionHistoryDto.setTransferredAmount(a.getTransferredAmount());
				transactionHistoryDto.setTransferredDate(a.getTransferredDate());
				listTransactionHistoryDto.add(transactionHistoryDto);

			});
			return listTransactionHistoryDto;
		} else {
			throw new NoTransactionRecordFoundException();
		}
	}

	@Override
	public List<TransactionDetail> findByTransferredAmount(int weeks) {
		LocalDate fromDate = LocalDate.now().minusWeeks(weeks);
		LocalDate toDate = LocalDate.now();

		return transactionRepository.findByTransferredAmount(fromDate, toDate);

	}

}
