package com.ing.stock.service;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ing.stock.dto.ResponseStockDto;
import com.ing.stock.entity.Stock;
import com.ing.stock.repository.StockRepository;

@RunWith(MockitoJUnitRunner.class)
public class TestStockService {

	@InjectMocks
	StockServiceImpl stockServiceImpl;

	@Mock
	StockRepository stockRepository;

	@Mock
	RestTemplate restTemplate;

	Stock stock;

	Long stockId;

	@Before
	public void setup() {
		stockId = 1L;
		stock = new Stock();
		stock.setStockId(1L);
		stock.setStockName("SBI");
		stock.setStockPrice(50D);
		stock.setDescription("bank");

	}

	@Test
	public void getStocksTest() throws IOException {

		String apiResponse = "{\r\n" + "    \"Global Quote\": {\r\n" + "        \"01. symbol\": \"SBI\",\r\n"
				+ "        \"02. open\": \"9.0400\",\r\n" + "        \"03. high\": \"9.1400\",\r\n"
				+ "        \"04. low\": \"9.0210\",\r\n" + "        \"05. price\": \"9.0900\",\r\n"
				+ "        \"06. volume\": \"4043\",\r\n" + "        \"07. latest trading day\": \"2019-09-19\",\r\n"
				+ "        \"08. previous close\": \"9.0300\",\r\n" + "        \"09. change\": \"0.0600\",\r\n"
				+ "        \"10. change percent\": \"0.6645%\"\r\n" + "    }\r\n" + "}";

		Mockito.when(restTemplate.exchange(
				"https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=SBI&apikey=JAWMQTBSYN26FR3M",
				HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
				}, 1L)).thenReturn(ResponseEntity.ok(apiResponse));

		Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));
		ResponseStockDto responseStockDto = stockServiceImpl.getStocks(1L);

		assertNotNull(responseStockDto);

	}

	@Test(expected = NullPointerException.class)
	public void testGetStocks() throws IOException {
		Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));
		ResponseStockDto responseStockDto = stockServiceImpl.getStocks(1L);
		Assert.assertEquals("SBI", responseStockDto.getStockName());
	}
}
