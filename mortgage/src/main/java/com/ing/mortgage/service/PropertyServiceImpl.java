package com.ing.mortgage.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ing.mortgage.dto.RequestPropertyDto;
import com.ing.mortgage.dto.ResponsePropertyDto;
import com.ing.mortgage.entity.Property;
import com.ing.mortgage.repository.PropertyRepository;

public class PropertyServiceImpl implements PropertyService{
	
	@Autowired
	PropertyRepository propertyRepository;

	@Override
	public ResponsePropertyDto save(RequestPropertyDto requestPropertyDto) {
		Property property = new Property();
		property.setPropertyName(requestPropertyDto.getPropertyName());
		property.setPropertyType(requestPropertyDto.getPropertyType());
		property.setSalary(requestPropertyDto.getSalary());
		property.setPropertyAddress(requestPropertyDto.getPropertyAddress());
		property.setPropertyValue(requestPropertyDto.getPropertyValue());
		property.setUserId(requestPropertyDto.getUserId());
		Property responseProperty = propertyRepository.save(property);
		if(responseProperty == null)
			throw new NullPointerException();
		ResponsePropertyDto responsePropertyDto = new ResponsePropertyDto();
		responsePropertyDto.setMessage("property added successfully");
		responsePropertyDto.setPropertyId(responseProperty.getPropetyId());
		return responsePropertyDto;
	}

}
