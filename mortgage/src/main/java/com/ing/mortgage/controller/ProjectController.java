package com.ing.mortgage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.mortgage.dto.RequestPropertyDto;
import com.ing.mortgage.dto.ResponsePropertyDto;
import com.ing.mortgage.service.PropertyService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("property")
public class ProjectController {

	@Autowired
	PropertyService propertyService;

	@PostMapping("/save")
	public ResponseEntity<ResponsePropertyDto> saveProperty(@RequestBody RequestPropertyDto requestPropertyDto) {
		ResponsePropertyDto responsePropertyDto = propertyService.save(requestPropertyDto);
		return new ResponseEntity<>(responsePropertyDto, HttpStatus.CREATED);
	}

}
