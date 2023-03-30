package com.brokerApplication.services;

import java.util.List;
import com.brokerApplication.entities.Broker;

public interface BrokerServices {
	
	public Broker addBroker(Broker broker);
	
	public Broker viewBrokerById(Integer brokerId);
	
	public Broker removeBrokerById(Integer brokerId);
	
	public Broker editBroker(Broker broker);
	
	public List<Broker> listAllBrokers();
}
