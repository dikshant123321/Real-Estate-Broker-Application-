package com.brokerApplication.services;

import java.util.List;

import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.BrokerOffer;
import com.brokerApplication.entities.CustomerOffer;
import com.brokerApplication.exceptions.DealException;

public interface DealService {
	public Deal addDealOfferFromCustomer(CustomerOffer offer);
	public List<Deal> getAllDeals();
	public Deal setDealOfferFromBroker(BrokerOffer brokerOffer);
	public String approveDeal(BrokerOffer brokerOffer)throws  DealException;
	public String AbandonedDeal(Integer did,Integer bid)throws  DealException;
	public Deal getDealbyID(Integer dealid);
}
