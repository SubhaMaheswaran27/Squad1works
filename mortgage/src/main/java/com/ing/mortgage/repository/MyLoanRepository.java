package com.ing.mortgage.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ing.mortgage.entity.MyLoan;

public interface MyLoanRepository extends JpaRepository<MyLoan, Long> {

	List<MyLoan> findByUserId(Long userId);

}
