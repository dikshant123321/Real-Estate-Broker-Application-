package com.brokerApplication.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brokerApplication.entities.Broker;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.Property;
import com.brokerApplication.entities.PropertySchedule;
import com.brokerApplication.entities.PropertyStatus;
import com.brokerApplication.exceptions.PropertyException;
import com.brokerApplication.repositorys.PropertyRepository;
import com.brokerApplication.repositorys.PropertyScheduleRepository;

@Service
public class PropertyServiceImple implements PropertyService{

	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
    private BrokerServices bs;
	
	@Autowired
	private CustomerService cs;
	
	@Autowired
	private PropertyScheduleService ps;
	
	@Override
	public Property addProperty(Property property, Integer brokerId) {
		
		Broker broker = bs.viewBrokerById(brokerId);
		
		
		property.setIsAvailable(true);
		property.setBroker(broker);
		
		broker.getListOfProperties().add(property);
		
		return propertyRepository.save(property);
	}

	@Override
	public Property editPropertyById(Property property) throws PropertyException {
	     Optional<Property> opt=propertyRepository.findById(property.getPropertyId());
	     if(opt.isPresent())
	     { 
	    	 return propertyRepository.save(property);
	     }
	     else
	     {
	    	 throw new PropertyException("Property does not exist...");
	     }
	}

	@Override
	public List<Property> viewListOfProperties() throws PropertyException {
		List<Property> property=propertyRepository.findAll();
		
		if(!property.isEmpty())
		{
			return property;
		}
		else
		{
			throw new PropertyException("Property does not exist...");
		}
		
	}

	@Override
	public Property viewPropertyById(Integer propertyId) throws PropertyException {
		Optional<Property> opt= propertyRepository.findById(propertyId);
		
		if(opt.isPresent())
		{
			return opt.get();
		}
		else
		{
			throw new PropertyException("Property does not exist...");
		}
		
	}

	@Override
	public Property removePropertyById(Integer propertyId) throws PropertyException {
		Property st=propertyRepository.findById(propertyId).orElseThrow(() -> new PropertyException("Property with propertyId "+propertyId+"does not exit.."));
		
		propertyRepository.delete(st);
		
		return st;
	}

	@Override
	public void rentPropertyById(Deal deal) {
		
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		
		Property property = deal.getProperty();
		
		PropertyStatus previousStatus = property.getPropertyStatus();
		
		long startDelay = ChronoUnit.MINUTES.between(deal.getRentStartPeriod(), LocalDate.now())*60;
		
		scheduler.schedule(()->{
			//rent started
			cs.addNewPropertyById(deal.getCustomer().getUserId(), property);
        	
    		property.setPropertyStatus(PropertyStatus.RESERVED);
    		property.setIsAvailable(false);
    		property.setCustomer(deal.getCustomer());
    		
    		propertyRepository.save(property);
    		
		}, startDelay, TimeUnit.SECONDS);
		
		long endDelay = ChronoUnit.MINUTES.between(deal.getRentEndPeriod(), LocalDate.now())*60;
        
		//adding property schedule
		PropertySchedule propertySchedule = ps.markPropertyScheduled(new PropertySchedule(property.getPropertyId(), deal.getRentStartPeriod(), deal.getRentEndPeriod(), PropertyStatus.RESERVED));
		
		scheduler.schedule(()->{
			 //tasks to be done on ending day
        	cs.removeCustomerPropertyById(deal.getDealid(), deal.getProperty().getPropertyId());
        	
        	property.setPropertyStatus(previousStatus);
        	property.setIsAvailable(true);
        	property.setCustomer(null);
        	
        	propertyRepository.save(property);
        	
        	//removing property schedule
        	ps.removePropertySchedule(propertySchedule.getPropertyId());
    		
		}, endDelay, TimeUnit.SECONDS);
		
	}

	@Override
	public Property buyPropertyById(Deal deal) {
		
		Property property = deal.getProperty();
		
    	
		property.setPropertyStatus(PropertyStatus.SOLD);
		property.setIsAvailable(false);
		property.setCustomer(deal.getCustomer());
		
		cs.addNewPropertyById(deal.getCustomer().getUserId(), property);
		return propertyRepository.save(property);

	}
	
}
