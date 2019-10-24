package com.ing.quote.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.quote.dto.PurchaseDto;
import com.ing.quote.entiry.Purchase;
import com.ing.quote.repository.PurchaseRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	PurchaseRepository purchaseRepository;

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	public PurchaseDto purchase(Long userId, Long stockId, int quantity) {
		PurchaseDto purchaseDto = null;
		String response = restTemplate.exchange("http://localhost:9009/stock/stock/stock/{stockId}", HttpMethod.GET,
				null, new ParameterizedTypeReference<String>() {
				}, stockId).getBody();
		System.out.println("Message" + response);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(response);

			Double stockPrice = node.at("/stockPrice").asDouble();

			Double totalstockPrice = (stockPrice * quantity);
			Double brokerageAmount = (totalstockPrice * 0.5);
			Double totalAmount = totalstockPrice + brokerageAmount;

			Purchase purchase = new Purchase();
			purchase.setQuantity(quantity);
			purchase.setStockId(stockId);
			purchase.setUserId(userId);
			purchase.setTotalAmount(totalAmount);

			Purchase responseOrder = purchaseRepository.save(purchase);
			purchaseDto = new PurchaseDto();
			purchaseDto.setPurchaseId(responseOrder.getPurchaseId());
			purchaseDto.setMessage("stock purchased successfully");
		} catch (IOException e) {

			e.printStackTrace();
		}

		return purchaseDto;
	}

}
