package com.ing.bank.controller;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ing.bank.dto.RequestLoginDto;
import com.ing.bank.service.LoginServiceImpl;
import com.ing.bank.util.ContentTypeTestCase;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class LoginControllerTest {

	MockMvc mockMvc;

	@InjectMocks
	LoginController loginController;

	@Mock
	LoginServiceImpl loginServiceImpl;

	RequestLoginDto requestLoginDto;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
		requestLoginDto = new RequestLoginDto();
		requestLoginDto.setEmail("subha@gmail.com");
		requestLoginDto.setPassword("subha");
	}

	@Test
	public void testLogin() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/login").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(ContentTypeTestCase.asJsonString(requestLoginDto)))
				.andExpect(status().isOk());
	}
}
