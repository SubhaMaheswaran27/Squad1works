package com.ing.mortgage.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestPropertyDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Double salary;
	private String propertyName;
	private String propertyType;
	private String propertyAddress;
	private Double propertyValue;
	private Long userId;
}
