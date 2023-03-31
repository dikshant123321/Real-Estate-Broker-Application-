package com.brokerApplication.controllers;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PaymentDetails {

	private String cardNo;
	private String cardHolderName;
	private String cvv;
	@JsonFormat(pattern = "MM-yyyy")
	private LocalDate expiryDate;
	
	
}
