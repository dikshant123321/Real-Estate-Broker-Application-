package com.brokerApplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brokerApplication.entities.Bill;
import com.brokerApplication.entities.BillStatus;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.PaymentDetails;
import com.brokerApplication.entities.PaymentReceipt;
import com.brokerApplication.exceptions.BillingException;
import com.brokerApplication.repositorys.BillingRepository;

import io.swagger.v3.oas.annotations.servers.Server;

@Service
public class BillingServiceImpl implements BillingService {

	@Autowired
	private BillingRepository billingRepository;
	
	@Autowired
	private DealService ds;
	
	@Override
	public Deal payBillForDeal(PaymentDetails paymentDetails) {
		
		Deal deal = ds.getDealbyID(paymentDetails.getDealId());
		
		Bill bill = deal.getBill();
		
		if(bill.getBillStatus() == BillStatus.PAID) throw new BillingException("Bill's status is showing paid already.");
		
		if(paymentDetails.getDealId() != bill.getDeal().getDealid()) throw new BillingException("Deal with Id "+paymentDetails.getDealId()+" passed with the payment deatils is not related to the one in the Bill.");
		
		if(((int)paymentDetails.getPaymentAmount().doubleValue() - (int)bill.getTotalPayableAmout().doubleValue()) != 0) throw new BillingException("Total amount which needs to be paid is "+bill.getTotalPayableAmout()+" but only receiving "+paymentDetails.getPaymentAmount());
		
		bill.setBillStatus(BillStatus.PAID);
		
		// set bill in deal and save 
		PaymentReceipt paymentReceipt = ds.updateBillStatusInDealById(deal, bill);
		billingRepository.save(bill);
		
		return ds.finaliseDealAfterPayment(paymentReceipt);
		
	}

}
