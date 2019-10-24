package com.ing.mortgage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ing.mortgage.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {
	
	@Query("select l from Loan l where l.loanAmount<=:loanAmount")
	List<Loan> findByLoanAmount(Double loanAmount);

}
