package com.ing.bank.service;

import com.ing.bank.dto.RegisterRequestDto;
import com.ing.bank.dto.RegisterResponseDto;

public interface RegisterService {

	RegisterResponseDto register(RegisterRequestDto registerRequestDto);

}
