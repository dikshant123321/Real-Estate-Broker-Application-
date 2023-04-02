package com.brokerApplication.services;

import java.util.List;

import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.PaymentReceipt;
import com.brokerApplication.entities.Bill;
import com.brokerApplication.entities.BrokerOffer;
import com.brokerApplication.entities.CustomerOffer;

public interface DealService {

	//customer use
	public Deal addDealOfferFromCustomer(CustomerOffer customerOffer);
	public Deal editDealOfferFromCustomerByDealId(Integer dealId, CustomerOffer customerOffer);
	public Deal acceptDealForCustomer(Integer dealId, Integer customerId);
	public Deal rejectDealForCustomer(Integer dealId, Integer customerId);
	public Deal deleteDealOfferForCustomer(Integer dealId, Integer customerId);
	
	//admin use
	public List<Deal> getAllDeals();
	public Deal getDealById(Integer dealId);
	
	//broker use
	public Deal setDealOfferFromBroker(BrokerOffer brokerOffer);
	public Deal approveDealForBroker(Integer dealId, Integer brokerId);
	public Deal abandonedDealForBroker(Integer dealId, Integer brokerId);

	//internal use	
	public PaymentReceipt updateBillStatusInDealById(Deal deal ,Bill bill);
	public Deal finaliseDealAfterPayment(PaymentReceipt paymentReceipt);
	public Deal getDealbyID(Integer dealid);
}
