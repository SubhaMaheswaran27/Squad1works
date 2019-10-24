package com.ing.mortgage.controller;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.mortgage.dto.RequestUserDto;
import com.ing.mortgage.service.UserService;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;

	MockMvc mockMvc;

	RequestUserDto requestUserDto = null;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

		requestUserDto = new RequestUserDto();
		requestUserDto.setFirstName("subha");
		requestUserDto.setLastName("Maheswaran");
		requestUserDto.setGender("female");
	}

	@Test
	public void testRegister() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/save").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(asJsonString(requestUserDto)))
				.andExpect(status().isCreated());
	}

	@Test
	public void testMyLoans() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/loans").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(asJsonString(Mockito.anyString())))
				.andExpect(status().isFound());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
