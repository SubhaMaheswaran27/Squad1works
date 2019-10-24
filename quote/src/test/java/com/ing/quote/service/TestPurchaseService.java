package com.ing.quote.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ing.quote.entiry.Purchase;
import com.ing.quote.repository.PurchaseRepository;

@RunWith(MockitoJUnitRunner.class)
public class TestPurchaseService {
	@InjectMocks
	PurchaseServiceImpl purchaseServiceImpl;

	@Mock
	PurchaseRepository purchaseRepository;
	
	Purchase purchase;
	@Before
	public void setup() {
		purchase = new Purchase();
		purchase.setPurchaseId(1L);
		purchase.setQuantity(2);
		purchase.setStockId(1L);
		purchase.setTotalAmount(300D);
		purchase.setUserId(1L);
	}
	@Test
	public void testPurchase() {
		Mockito.when(purchaseRepository.save(Mockito.any())).thenReturn(purchase);
	}
	

}
