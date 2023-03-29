package com.brokerApplication.exceptions;

public class BrokerException extends RuntimeException{
	
	public BrokerException() {
		
	}
	
	public BrokerException(String msg) {
		super(msg);
	}
}
