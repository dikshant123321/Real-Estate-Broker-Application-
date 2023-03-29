package com.brokerApplication.services;

import java.util.List;
import java.util.Optional;

import com.brokerApplication.entities.Broker;
import com.brokerApplication.exceptions.BrokerException;
import com.brokerApplication.repositorys.BrokerDao;

public class BrokerServicesImpl implements BrokerServices{

	private BrokerDao brokerDao;
	
	@Override
	public Broker addBroker(Broker broker) {
		Broker newBroker = brokerDao.save(broker);
		return newBroker;
	}
	
	@Override
	public Broker viewBroker(Integer brokerId) {
		Optional<Broker> opt= brokerDao.findById(brokerId);
		
		if(opt.isPresent()) {
			Broker broker = opt.get();	
			return broker;
		}else {
			throw new BrokerException("Broker either not exist or wrong broker id entered.");		
		}
	}
	
	@Override
	public Broker removeBroker(Integer brokerId) {
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
}
