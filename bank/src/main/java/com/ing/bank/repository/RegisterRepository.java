package com.ing.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.bank.entity.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {

	Register findByEmail(String email);

	Register findByUserId(Long userId);

	Register findByEmailAndPassword(String email, String password);

}
