package com.ing.bank.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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

import com.ing.bank.dto.ListBeneficiaryDto;
import com.ing.bank.dto.RequestBeneficiaryDto;
import com.ing.bank.service.BeneficiaryServiceImpl;
import com.ing.bank.util.ContentTypeTestCase;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class BeneficiaryControllerTest {
	@InjectMocks
	BeneficiaryController beneficiaryController;

	@Mock
	BeneficiaryServiceImpl beneficiaryServiceImpl;
	MockMvc mockMvc;
	RequestBeneficiaryDto requestBeneficiaryDto;
	ListBeneficiaryDto listBeneficiaryDto;
	List<ListBeneficiaryDto> beneficiaryDto;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(beneficiaryController).build();

		requestBeneficiaryDto = new RequestBeneficiaryDto();
		requestBeneficiaryDto.setBeneficiaryAccountNumber("76374857364756");
		requestBeneficiaryDto.setBeneficiaryName("subha");
		requestBeneficiaryDto.setUserId(1L);

		listBeneficiaryDto = new ListBeneficiaryDto();
		listBeneficiaryDto.setBeneficiaryAccountNumber("76374857364756");
		listBeneficiaryDto.setBeneficiaryId(1L);

		beneficiaryDto = new ArrayList<>();
		beneficiaryDto.add(listBeneficiaryDto);

	}

	@Test
	public void testAddBeneficiary() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/beneficiary").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(ContentTypeTestCase.asJsonString(requestBeneficiaryDto)))
				.andExpect(status().isCreated());
	}

	@Test
	public void testViewBeneficiaries() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/beneficiary/{userId}", 1L)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(ContentTypeTestCase.asJsonString(Mockito.anyLong()))).andExpect(status().isFound());
	}
}
