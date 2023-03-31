package com.brokerApplication.controllers;

import java.time.LocalDateTime;

import com.brokerApplication.entities.Broker;
import com.brokerApplication.entities.Customer;
import com.brokerApplication.entities.Deal;

public class PaymentReceipt {
	
	private Deal deal;
	private Customer customer;
	private Broker broker;
	private LocalDateTime dateAndTime;
	
}
