package com.ing.mortgage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ing.mortgage.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {

}
