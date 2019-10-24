package com.ing.producer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ing.producer.entity.User;
import com.ing.producer.repository.UserRepository;

public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getUser() {

		return userRepository.findAll();
	}

}
