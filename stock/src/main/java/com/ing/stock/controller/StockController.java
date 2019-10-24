package com.ing.stock.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.stock.dto.ResponseStockDto;
import com.ing.stock.service.StockService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("stock")
public class StockController {

	@Autowired
	StockService stockService;

	@GetMapping("/stock/{stockId}")
	public ResponseEntity<ResponseStockDto> getStocks(@PathVariable("stockId") Long stockId) throws IOException {
		ResponseStockDto stock = stockService.getStocks(stockId);
		return new ResponseEntity<>(stock, HttpStatus.FOUND);
	}
}
