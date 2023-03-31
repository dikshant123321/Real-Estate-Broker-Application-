package com.brokerApplication.services;

import java.util.List;

import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.BrokerOffer;
import com.brokerApplication.entities.CustomerOffer;
import com.brokerApplication.exceptions.DealException;

public interface DealService {

	public Deal addDealOfferFromCustomer(CustomerOffer customerOffer);
	public Deal editDealOfferFromCustomerByDealId(Integer dealId, CustomerOffer customerOffer);
	public List<Deal> getAllDeals();
	public Deal setDealOfferFromBroker(BrokerOffer brokerOffer);
	public Deal getDealbyID(Integer dealid);
	public Deal approveDeal(BrokerOffer brokerOffer);
	public Deal AbandonedDeal(Integer dealId);
}
