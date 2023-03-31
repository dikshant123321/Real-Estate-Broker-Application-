package com.brokerApplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brokerApplication.entities.Broker;


import com.brokerApplication.entities.Customer;

import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.Property;
import com.brokerApplication.exceptions.BrokerException;
import com.brokerApplication.exceptions.CustomerException;
import com.brokerApplication.repositorys.BrokerDao;

@Service
public class BrokerServicesImpl implements BrokerServices{
	
	@Autowired
	private BrokerDao brokerDao;
	
	@Override
	public Broker addBroker(Broker broker) {
		Broker newBroker = brokerDao.save(broker);
		return newBroker;
	}
	
	@Override
	public Broker viewBrokerById(Integer brokerId) {
		Optional<Broker> opt= brokerDao.findById(brokerId);
		
		if(opt.isPresent()) {
			Broker broker = opt.get();	
			return broker;
		}else {
			throw new BrokerException("Broker either not exist or wrong broker id entered.");		
		}
	}
	
	@Override
	public Broker removeBrokerById(Integer brokerId) {
		Optional<Broker> opt= brokerDao.findById(brokerId);
		if(opt.isPresent()) {
			brokerDao.delete(opt.get());
			return (Broker)opt.get();
		 }else {
			 throw new BrokerException("Broker either not exist or wrong broker id entered.");
		 }
	}
	
	@Override
	public Broker editBroker(Broker broker) {
		Optional<Broker> opt= brokerDao.findById(broker.getUserId());
		
		 if(opt.isPresent()) {
			 brokerDao.save(broker);
		 }else {
			 throw new BrokerException("Broker either not exist or wrong broker id entered.");
		 }
	  
		return broker;
	}
	
	@Override
	public List<Broker> listAllBrokers(){
		List<Broker> brokers = brokerDao.findAll();
		if(brokers.size()==0)
			throw new BrokerException("All broker is registered with us.");
		else
			return brokers;
	}

	

	@Override
	public List<Deal> listBrokerHandlerDeals(Integer brokerId) {
		Broker bro=viewBrokerById(brokerId);
		List<Deal> list=bro.getListOfDeals();
		if(list.isEmpty()) throw new BrokerException("Hello "+bro.getBrokerName()+"/n"+" .. No Deal Listed ");
		return list;
	}
	
	public List<Property> getListOfPropertiesById(Integer brokerId) {
		
		Broker bro=viewBrokerById(brokerId);
		List<Property> list=bro.getListOfProperties();
		if(list.isEmpty()) throw new BrokerException("Hello "+bro.getBrokerName()+"/n"+" .. No properties where listed");
		return list;
		
	}

	@Override
	public Property getBrokerPropertyById(Integer brokerId ,Integer propertyId) {
		
		Broker broker = viewBrokerById(brokerId);
		
		List<Property> properties = broker.getListOfProperties();
		
		for(Property p: properties) if(p.getPropertyId() == propertyId) return p;
		
		throw new BrokerException("Broker with Id: "+brokerId+" has no property with Id: "+propertyId);
		
	}

	@Override
	public Deal addBrokerDealById(Integer brokerId, Deal deal) {
		
		Broker broker = viewBrokerById(brokerId);
		
		//Verify
		
		broker.getListOfDeals().add(deal);
		
		broker = brokerDao.save(broker);
		
		return deal;
		
	}

	@Override
	public Deal editBrokerDealById(Integer brokerId, Deal deal) {
		Broker broker = viewBrokerById(brokerId);
		
		//Verify
		
		Deal updatedDeal = null;
		
		for(Deal d: broker.getListOfDeals()) {
			
			if(deal.getDealid() == d.getDealid()) {
				
				updatedDeal = d;
				d = deal;
				break;
				
			}
			
		}
		
		if(updatedDeal == null) throw new CustomerException("Broker with Id "+brokerId+" doesn't have Deal with Id "+deal.getDealid());
		
		broker = brokerDao.save(broker);
		
		return deal;
		

	}
}
