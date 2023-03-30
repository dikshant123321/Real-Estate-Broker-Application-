package com.brokerApplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.brokerApplication.entities.Broker;
import com.brokerApplication.services.BrokerServices;

public class AdminController implements AdminControllerInterface{
	@Autowired
	private BrokerServices brokerServices;
	
	
	@GetMapping("/brokers")
	public ResponseEntity<List<Broker>> getAllBrokersHandler(){
		List<Broker> brokers= brokerServices.listAllBrokers();
		return new ResponseEntity<>(brokers,HttpStatus.FOUND);
	}
	
}
