package com.ing.quote.service;

import com.ing.quote.dto.PurchaseDto;

public interface PurchaseService {

	PurchaseDto purchase(Long userId, Long stockId, int quantity);

}
