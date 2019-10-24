package com.ing.quote.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PurchaseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long purchaseId;
	private String message;
}
