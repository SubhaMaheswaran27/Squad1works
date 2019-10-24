package com.ing.mortgage.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseUserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String messaage;
	private Long userId;
	private String accountNumber;

}
