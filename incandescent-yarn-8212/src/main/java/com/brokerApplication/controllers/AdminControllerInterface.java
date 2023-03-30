package com.brokerApplication.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.brokerApplication.entities.Broker;

public interface AdminControllerInterface {
	
	public ResponseEntity<List<Broker>> getAllBrokersHandler();
}
