package com.brokerApplication.services;

import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.PaymentDetails;

public interface BillingService {

	public Deal payBillForDeal(PaymentDetails paymentDetails);
	
}
