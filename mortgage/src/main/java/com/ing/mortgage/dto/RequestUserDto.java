package com.ing.mortgage.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestUserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	private String gender;
	private String dateOfBirth;
	private String email;
	private Long mobileNumber;
	private String propertyName;
	private String propertyType;
	private String propertyAddress;
	private Double propertyValue;
}
