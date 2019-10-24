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

import com.ing.bank.service.AccountServiceImpl;
import com.ing.bank.util.ContentTypeTestCase;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class AccountControllerTest {

	MockMvc mockMvc;

	@InjectMocks
	AccountController accountController;

	@Mock
	AccountServiceImpl accountServiceImpl;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
	}

	@Test
	public void testAccountSummary() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/summary/{userId}", 1L).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(ContentTypeTestCase.asJsonString(Mockito.anyLong())))
				.andExpect(status().isFound());
	}
}
