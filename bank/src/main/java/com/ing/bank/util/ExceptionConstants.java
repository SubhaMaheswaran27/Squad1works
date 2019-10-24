package com.ing.bank.util;

public class ExceptionConstants {
	private ExceptionConstants() {

	}

	public static final String INVALID_MOBILE_NUMBER = "Please enter a valid mobile number";
	public static final String INVALID_EMAIL = "please enter a valid email Id";
	public static final String EXIST_EMAIL = "Email Id already exist";
	public static final String INVALID_AGE = "Age should be greater than 18";
	public static final String REGISTRATION_FAILED = "Registration failed..";
	public static final String USER_NOT_FOUND = "Invalid credentials";
	public static final String BENEFICIARY_NOT_FOUND = "Beneficiary account not exist";
	public static final String ADD_BENEFICIARY_FAILED = "Add beneficiary failed..";
	public static final String NO_BENEFICIARY_ADDED = "No beneficiaries have added";
	public static final String ACCOUNT_NOT_FOUND = "No accounts found..";
	public static final String INVALID_BENEFICIARY_ACCOUNT = "Invalid beneficiary account";
	public static final String INVALID_AMOUNT = "Please enter a valid amount";
	public static final String TRANSACTION_FAILED = "Transaction failed";
	public static final String WEEK_HISTORY_NOT_FOUND = "No transactions found for last week";
	public static final String MONTH_HISTORY_NOT_FOUND = "No transactions found";
	public static final String NOT_A_BENEFICIARY = "It's not a beneficiary account";
	public static final String ACCOUNT_SHOULD_NOT_SAME = "Can't do transactions between same account ";
	public static final String DUPLICATE_BENEFICIARY = "Duplicate beneficiary found";

}
