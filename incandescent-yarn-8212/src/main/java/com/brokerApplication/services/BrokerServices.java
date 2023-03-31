package com.brokerApplication.services;

import java.util.List;

import com.brokerApplication.entities.Broker;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.Property;

public interface BrokerServices {
	
	public Broker addBroker(Broker broker);
	
	public Broker viewBrokerById(Integer brokerId);
	
	public Broker removeBrokerById(Integer brokerId);
	
	public Broker editBroker(Broker broker);
	
	public List<Broker> listAllBrokers();
	

	public List<Property>  getListOfPropertiesById(Integer brokerId);
	
	public List<Deal> listBrokerHandlerDeals(Integer brokerId);

	
	public Property getBrokerPropertyById(Integer brokerId ,Integer propertyId);
	
	public Deal addBrokerDealById(Integer brokerId, Deal deal);
	
	public Deal editBrokerDealById(Integer brokerId, Deal deal);
	

}
