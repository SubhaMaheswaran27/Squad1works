package com.ing.mortgage.service;

import com.ing.mortgage.dto.RequestPropertyDto;
import com.ing.mortgage.dto.ResponsePropertyDto;

public interface PropertyService {

	ResponsePropertyDto save(RequestPropertyDto requestPropertyDto);

}
