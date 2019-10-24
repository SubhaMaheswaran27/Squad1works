package com.ing.producer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ing.producer.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
