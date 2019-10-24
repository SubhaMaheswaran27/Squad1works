package com.ing.bank.service;

import com.ing.bank.dto.ResponseAccountDto;

public interface AccountService {

	ResponseAccountDto accountSummary(Long userId);

}
