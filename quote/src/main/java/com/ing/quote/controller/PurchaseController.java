package com.ing.quote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.quote.dto.PurchaseDto;
import com.ing.quote.service.PurchaseService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("quote")
public class PurchaseController {

	@Autowired
	PurchaseService purchaseService;

	@PostMapping("/purchase/{userId},{stockId},{Quantity}")
	public PurchaseDto purchase(@PathVariable Long userId, @PathVariable Long stockId, @PathVariable int Quantity) {
		PurchaseDto purchaseDto = purchaseService.purchase(userId, stockId, Quantity);
		return purchaseDto;
	}
}
