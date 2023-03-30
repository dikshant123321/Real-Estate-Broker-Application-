package com.brokerApplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brokerApplication.entities.Broker;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.Property;
import com.brokerApplication.exceptions.BrokerException;
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
	public List<Property> listBrokerHandlerProperties(Integer brokerId) {
		Broker bro=viewBrokerById(brokerId);
		List<Property> list=bro.getListOfProperties();
		if(list.isEmpty()) new BrokerException("Hello "+bro.getBrokerName()+"/n"+" .. No properties where listed");
		return list;
	}
	
	@Override
	public List<Deal> listBrokerHandlerDeals(Integer brokerId) {
		Broker bro=viewBrokerById(brokerId);
		List<Deal> list=bro.getListOfDeals();
		if(list.isEmpty()) new BrokerException("Hello "+bro.getBrokerName()+"/n"+" .. No Deal Listed ");
		return list;
	}
}
