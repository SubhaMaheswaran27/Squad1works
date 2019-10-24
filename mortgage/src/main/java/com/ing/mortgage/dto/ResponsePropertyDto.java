package com.ing.mortgage.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponsePropertyDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;
	private Long propertyId;

}
