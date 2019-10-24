package com.ing.bank.service;

import java.util.List;

import com.ing.bank.dto.ListBeneficiaryDto;
import com.ing.bank.dto.RequestBeneficiaryDto;
import com.ing.bank.dto.ResponseBeneficiaryDto;

public interface BeneficiaryService {

	ResponseBeneficiaryDto addBeneficiary(RequestBeneficiaryDto requestBeneficiaryDto);

	List<ListBeneficiaryDto> viewBeneficiaries(Long userId);

}
