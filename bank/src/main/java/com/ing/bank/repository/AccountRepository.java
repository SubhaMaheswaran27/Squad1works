package com.ing.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.bank.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByAccountNumber(String fromAccount);

	Account findByUserId(Long userId);

}
