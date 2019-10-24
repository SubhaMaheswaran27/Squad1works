package com.ing.mortgage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ing.mortgage.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
