package com.brokerApplication.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brokerApplication.entities.Broker;
import com.brokerApplication.services.BrokerServices;

@RestController
public class BrokerController {
	
	@Autowired
	private BrokerServices brokerServices;
	
	private List<Broker> brokers= new ArrayList<>();
	
	@PostMapping("/brokers")
	public ResponseEntity<Broker> registerBrokerHandler(@RequestBody Broker broker){
		
		Broker newBroker = brokerServices.addBroker(broker);
		return new ResponseEntity<>(newBroker, HttpStatus.CREATED);
	}
	
	@GetMapping("/brokers")
	public ResponseEntity<List<Broker>> getAllBrokersHandler(){
		List<Broker> brokers= brokerServices.listAllBrokers();
		return new ResponseEntity<>(brokers,HttpStatus.FOUND);
	}
	
	
	@GetMapping("/brokers/{id}")
	public ResponseEntity<Broker> getBrokerByIdHandler(@PathVariable Integer id){
		Broker broker = brokerServices.viewBrokerById(id);
		return new ResponseEntity<>(broker,HttpStatus.FOUND);
	}
	
	@DeleteMapping("/brokers/{id}")
	public ResponseEntity<Broker> deleteBrokerByIdHandler(@PathVariable Integer id){
		Broker broker = brokerServices.removeBrokerById(id);
		return new ResponseEntity<>(broker,HttpStatus.OK);
	}
	
	@PutMapping("/brokers/{Id}")
	@ResponseBody
	public ResponseEntity<Broker> updateBrokerByIdHandler(@PathVariable Integer id, Broker broker){
		Broker newbroker = brokerServices.editBroker(broker);
		return new ResponseEntity<>(newbroker,HttpStatus.OK);
	}
	
	
}
