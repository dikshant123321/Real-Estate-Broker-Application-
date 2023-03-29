package com.brokerApplication.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brokerApplication.entities.Broker;

public interface BrokerDao extends JpaRepository<Broker, Integer> {
	
	//Spring Data JPA f/w will provide the implementation of this interface 
	// and register that implementation object with the spring container as a Spring bean
		
}