package com.ing.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.bank.entity.Beneficiary;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

	List<Beneficiary> findByUserAccountNumber(String accountNumber);

	Beneficiary findByUserAccountNumberAndBeneficiaryAccountNumber(String accountNumber, String accountNumber2);

}
