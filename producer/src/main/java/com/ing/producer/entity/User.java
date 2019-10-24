package com.ing.producer.entity;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userId;
	private String userName;
	private int Age;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
