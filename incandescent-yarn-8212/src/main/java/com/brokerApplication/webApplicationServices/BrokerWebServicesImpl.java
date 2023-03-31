package com.brokerApplication.webApplicationServices;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.brokerApplication.entities.Broker;
import com.brokerApplication.entities.Property;
import com.brokerApplication.repositorys.BrokerDao;

@Service
public class BrokerWebServicesImpl implements BrokerWebServices{
	private BrokerDao brokerDao;
	
	public BrokerWebServicesImpl(BrokerDao brokerDao) {
		this.brokerDao = brokerDao;
	}

	@Override
	public List<Broker> getAllBrokers() {
		return brokerDao.findAll();
	}

	@Override
	public void delbrokerbyid(Integer id) {
		brokerDao.deleteById(id);
	}

	@Override
	public Broker saveBr(Broker b) {
		return brokerDao.save(b);
	}

	@Override
	public List<Property> getAllProperties(Integer id) {
		Optional<Broker> b = brokerDao.findById(id);
		return b.get().getListOfProperties();
	}
}
