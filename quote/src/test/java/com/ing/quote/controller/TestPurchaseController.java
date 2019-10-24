package com.ing.quote.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ing.quote.dto.PurchaseDto;
import com.ing.quote.service.PurchaseService;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class TestPurchaseController {

	@InjectMocks
	PurchaseController purchaseController;

	@Mock
	PurchaseService purchaseService;

	MockMvc mockMvc;
	
	PurchaseDto purchaseDto;
	@Before
	public void setup() {
		purchaseDto = new PurchaseDto();
		purchaseDto.setMessage("purchase scucess");
	}

	@Test(expected = NullPointerException.class)
	public void testPurchase() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/purchase/{userId},{stockId},{Quantity}",1L,1L,1L).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isFound());
	}
}
