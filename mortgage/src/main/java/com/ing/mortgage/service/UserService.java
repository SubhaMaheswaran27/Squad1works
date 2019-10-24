package com.ing.mortgage.service;

import java.util.List;

import com.ing.mortgage.dto.RequestUserDto;
import com.ing.mortgage.dto.ResponseLoanDto;
import com.ing.mortgage.dto.ResponseUserDto;

public interface UserService {

	ResponseUserDto register(RequestUserDto requestUserDto);

	List<ResponseLoanDto> myLoans(String email);

}
