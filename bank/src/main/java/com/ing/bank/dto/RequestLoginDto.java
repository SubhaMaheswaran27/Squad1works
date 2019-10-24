package com.ing.bank.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestLoginDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String email;
	private String password;

}
