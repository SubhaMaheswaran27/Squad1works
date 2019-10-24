package com.ing.bank.service;

import com.ing.bank.dto.RequestLoginDto;
import com.ing.bank.dto.ResponseLoginDto;

public interface LoginService {

	ResponseLoginDto login(RequestLoginDto requestLoginDto);

}
