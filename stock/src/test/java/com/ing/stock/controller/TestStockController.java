package com.ing.stock.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.stock.dto.ResponseStockDto;
import com.ing.stock.service.StockService;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class TestStockController {

	@InjectMocks
	StockController stockController;

	@Mock
	StockService stockService;

	MockMvc mockMvc;

	ResponseStockDto responseStockDto;

	@Before
	public void setup() {
		responseStockDto = new ResponseStockDto();
		responseStockDto.setStockId(1L);
		responseStockDto.setStockName("SBI");
		responseStockDto.setStockPrice(50D);
	}

	@Test
	public void testGetStocks() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/stock").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).content(asJsonString(Mockito.anyLong())).andExpect(status().isFound());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
