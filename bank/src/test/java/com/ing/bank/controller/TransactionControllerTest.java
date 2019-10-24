package com.ing.bank.controller;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ing.bank.dto.RequestTransferDto;
import com.ing.bank.service.TransactionServiceImpl;
import com.ing.bank.util.ContentTypeTestCase;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class TransactionControllerTest {

	MockMvc mockMvc;

	@InjectMocks
	TransactionController transactionController;

	@Mock
	TransactionServiceImpl transactionServiceImpl;

	RequestTransferDto requestTransferDto;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
		requestTransferDto = new RequestTransferDto();
		requestTransferDto.setToAccount("43526789873524");
		requestTransferDto.setTransferredAmount(1000D);
	}

	@Test
	public void testTransfer() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/transfer").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(ContentTypeTestCase.asJsonString(requestTransferDto)))
				.andExpect(status().isOk());
	}

	@Test
	public void testWeekHistory() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/transactionSummary/{userId}", 1L)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(ContentTypeTestCase.asJsonString(Mockito.anyLong()))).andExpect(status().isFound());
	}

	@Test
	public void testMonthHistory() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/user/transactionSummary/{userId}/{year}/{monthName}", 1L, 2019, "SEPTEMBER")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isFound());
	}

}
