package com.brokerApplication.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brokerApplication.entities.Broker;
import com.brokerApplication.entities.BrokerOffer;
import com.brokerApplication.entities.Customer;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.DealStatus;
import com.brokerApplication.entities.CustomerOffer;
import com.brokerApplication.entities.Property;
import com.brokerApplication.exceptions.DealException;
import com.brokerApplication.repositorys.BrokerDao;
import com.brokerApplication.repositorys.DealRepo;
import com.brokerApplication.repositorys.PropertyRepository;


@Service
public class DealServiceImpl implements DealService{
	
	@Autowired
	DealRepo dr;
	
	@Autowired
	PropertyService ps;
	
	@Autowired
	CustomerService cs;
	
	@Autowired
	BrokerServices bs;
	
	
	
	/**
	 * This field only should be accessible by the Customer, to pass his offer to the Broker for the
	 * property, then the deal with pending status will be added to the both Customer and Broker
	 * side, so the Customer can see the status of the deal and Broker can perform the action on the offer.
	 * 
	 * @param CustomerOffer: accepting offer from the Customer
	 * @return Deal: the pending deal object
	 * 
	 */
	@Override
	public Deal addDealOfferFromCustomer(CustomerOffer customerOffer) {
		
		Broker broker = bs.viewBrokerById(customerOffer.getBrokerId());
		Customer customer = cs.viewCustomerById(customerOffer.getPropertyId()).getBody();
		Property property = null;
		
		List<Property> listOfProperties = broker.getListOfProperties();
		for(Property p: listOfProperties) {
			if(p.getPropertyId() == customerOffer.getPropertyId()) {
				property = p;
				break;
			}
		}
		
		if(property == null) throw new DealException("Broker has no property with propertyId: "+customerOffer.getPropertyId());
		
		if(!property.getIsAvailable()) throw new DealException("Property is not avaliable for sale.");
		
		if(customerOffer.getDealCost()<0) throw new DealException("Deal cost cannot be in negative.");
		
		if(customerOffer.getDealType() == null) throw new DealException("Deal type undefined! Either it could be Buy or Rent.");
		
		Deal deal = new Deal();
		
		deal.setDealCost(customerOffer.getDealCost());
		deal.setDealType(customerOffer.getDealType());
		deal.setCustomer(customer);
		deal.setProperty(property);
		deal.setBroker(broker);
		
		deal.setDealStatus(DealStatus.PENDING);
		deal.setDealDateTime(LocalDateTime.now());
		
		broker.getListOfDeals().add(deal);		
		customer.getListOfDeals().add(deal);
		
		return dr.save(deal);
	}

	@Override
	public List<Deal> getAllDeals() {
		
		List<Deal> list= dr.findAll();
		
		if(list.isEmpty()) throw new DealException("No deal is available to show.");
		
		return list;
	}

	/**
	 * This field only should be accessible by the Broker, to pass his offer to the Customer for the
	 * property, then the deal with pending status will be added to the both Customer and Broker
	 * side, so the Customer can see/update the status of the deal and Broker can perform the action on the offer.
	 * 
	 * @param BrokerOffer: accepting one of the deal object from the list of a broker
	 * @return Deal: negotiated deal object
	 */
	@Override
	public Deal setDealOfferFromBroker(BrokerOffer brokerOffer) {
		
		Broker broker = bs.viewBrokerById(brokerOffer.getBrokerId());
		Customer customer = cs.viewCustomerById(brokerOffer.getPropertyId()).getBody();
		Property property = null;
		
		List<Property> listOfProperties = broker.getListOfProperties();
		for(Property p: listOfProperties) {
			if(p.getPropertyId() == brokerOffer.getPropertyId()) {
				property = p;
				break;
			}
		}
		
		if(property == null) throw new DealException("Broker has no property with propertyId: \"+customerOffer.getPropertyId()");
		
		boolean valid = false;
		
		listOfProperties = customer.getListOfProperties();
		for(Property p: listOfProperties) {
			if(p.getPropertyId() == brokerOffer.getPropertyId()) {
				property = p;
				valid = true;
				break;
			}
		}
		
		if(!valid) throw new DealException("Customer with Id "+customer.getUserId()+" does not contain Property with id "+brokerOffer.getPropertyId());
		
		if(!property.getIsAvailable()) throw new DealException("Property not avaliable for sale");
		
		if(brokerOffer.getDealCost()<0) throw new DealException("Deal cost cannot be in negative.");
				
		Deal deal = dr.findById(brokerOffer.getDealId()).orElseThrow(()-> new DealException("No Deal available with Id: "+brokerOffer.getDealId()));
		
		if(deal.getDealStatus() != DealStatus.PENDING) throw new DealException("Deal status was expected to be PENDING but found "+deal.getDealStatus());
		
		deal.setDealDateTime(LocalDateTime.now());
		
		broker.getListOfDeals().add(deal);		
		customer.getListOfDeals().add(deal);
		
		return dr.save(deal);

	}

	@Override
	public String approveDeal(BrokerOffer brokerOffer) {
//		Optional<Deal> op1=dr.findById(did);
//		
//		if(!op1.isPresent()) throw new DealException("chose proper deal");
//		
//		Deal deal = op1.get();
//		Customer cus = deal.getCustomer();
//		Property prop= deal.getProperty();
//		
//		if(!prop.getOwnerId() ==  ().getBroId())throw DealException("choose correct property ");
//		
//		deal.setDealStatus(true);
//		op1.get();
//		
		return "Deal Done";
	}
	
	@Override
	public String AbandonedDeal(Integer did,Integer bid) throws DealException {
//		Optional<Deal> op1=dr.findById(did);
//		Optional<Broker> op2=br.findById(bid);
//		if(!op1.isPresent()) throw DealException("choose proper deal");
//		if(!op1.get().getProperty().getBroker().getBroId()==op2.get().getBroId())throw DealException("choose correct property ");
//		dr.delete(op1.get());
		return "Deal cancelled";
	}

	
	
}



