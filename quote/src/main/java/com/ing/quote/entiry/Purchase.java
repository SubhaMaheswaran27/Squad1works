package com.ing.quote.entiry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long purchaseId;
	private Long userId;
	private Long stockId;

	private int quantity;
	private Double totalAmount;
}
