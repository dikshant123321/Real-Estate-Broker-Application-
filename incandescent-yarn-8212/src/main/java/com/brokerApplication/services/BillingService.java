package com.brokerApplication.services;

import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.PaymentDetails;
import com.brokerApplication.entities.PaymentReceipt;

public interface BillingService {

	public Deal payBillForDeal(PaymentDetails paymentDetails);
	
}
