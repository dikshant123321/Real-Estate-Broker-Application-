package com.brokerApplication.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brokerApplication.entities.BillStatus;
import com.brokerApplication.entities.Bill;
import com.brokerApplication.entities.Broker;
import com.brokerApplication.entities.BrokerNotification;
import com.brokerApplication.entities.BrokerOffer;
import com.brokerApplication.entities.Customer;
import com.brokerApplication.entities.CustomerNotification;
import com.brokerApplication.entities.CustomerOffer;
import com.brokerApplication.entities.Deal;
import com.brokerApplication.entities.DealStatus;
import com.brokerApplication.entities.DealType;
import com.brokerApplication.entities.PaymentReceipt;
import com.brokerApplication.entities.Property;
import com.brokerApplication.entities.PropertyStatus;
import com.brokerApplication.exceptions.DealException;
import com.brokerApplication.repositorys.DealRepo;


@Service
public class DealServiceImpl implements DealService{
	
	@Autowired
	private DealRepo dr;
	
	@Autowired
	private PropertyService ps;
	
	@Autowired
	private CustomerService cs;
	
	@Autowired
	private BrokerServices bs;
	
	@Autowired
	private PropertyScheduleService pss;

	@Override
	public Deal getDealbyID(Integer dealid) {
		
		return dr.findById(dealid).orElseThrow(()-> new DealException("No Deal is available with id: "+dealid));
		
	}

	@Override
	public Deal getDealById(Integer dealId) {
		
		return dr.findById(dealId).orElseThrow(()->new DealException("No Deal is available with Id: "+dealId));
		
	}
	
	@Override
	public List<Deal> getAllDeals() {
		
		List<Deal> list= dr.findAll();
		
		if(list.isEmpty()) throw new DealException("No deal is available to show.");
		
		return list;
	}
	
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
		Customer customer = cs.viewCustomerById(customerOffer.getCustomerId());

		
		Property property = bs.getBrokerPropertyById(broker.getUserId(), customerOffer.getPropertyId());

		
		if(!property.getIsAvailable()) throw new DealException("Property is not avaliable for sale or rent.");
		
		isPropertySuitableForDeal(property, customerOffer.getDealType());
		
		if(customerOffer.getDealCost()<0) throw new DealException("Deal cost cannot be in negative.");
		
		if(customerOffer.getDealType() == null) throw new DealException("Deal type undefined! Either it could be Buy or Rent.");
		
		if(customerOffer.getDealType()==DealType.RENT) {
			
		        if(!(customerOffer.getStartPeriod() != null && customerOffer.getEndPeriod() != null)) throw new DealException("Start and end period must be specified for rent deals");
		        
		        if(customerOffer.getStartPeriod().isBefore(LocalDate.now())) throw new DealException("The start period for renting the property is in the past, but it must be in the future.");
		        
		        if(customerOffer.getEndPeriod().isBefore(customerOffer.getStartPeriod())) throw new DealException("The end period for renting the property is before the start period, but it must be after the start period.");
		        
		        pss.checkIsPropertyScheduledByPropertyIdAndBetweenStartDateAndEndDate(property.getPropertyId(), customerOffer.getStartPeriod(), customerOffer.getEndPeriod());
		}
		
		Deal deal = new Deal();
		
		deal.setDealCost(customerOffer.getDealCost());
		deal.setDealType(customerOffer.getDealType());
		if(deal.getDealType()==DealType.RENT) {
			deal.setRentStartPeriod(customerOffer.getStartPeriod());
			deal.setRentEndPeriod(customerOffer.getEndPeriod());
		}
		
		//--> customer making a deal means he agrees to the deal, but wait for the broker's response
		deal.setCustomerAgree(true);
		deal.setBrokerAgree(false);
		
		deal.setCustomer(customer);
		deal.setProperty(property);
		deal.setBroker(broker);
		deal.setDealStatus(DealStatus.PENDING);
		deal.setDealDateTime(LocalDateTime.now());
		
		Deal updatedDeal = dr.save(deal);
		
		broker.getListOfDeals().add(deal);		
		customer.getListOfDeals().add(deal);
		
		//notify the broker "Customer made a deal offer"
		String notificationMessage = "New "+((deal.getDealStatus()+"").toLowerCase())+" deal from "+customer.getCustomerName();
		BrokerNotification brokerNotification = new BrokerNotification(broker.getUserId(), deal.getDealid(), LocalDateTime.now(), notificationMessage);
		
		bs.sendNotificationToBrokerAboutDeal(broker.getUserId(), brokerNotification);
		
		return updatedDeal;
	}
	
	private boolean isPropertySuitableForDeal(Property property, DealType dealType) {
		
		PropertyStatus propertyStatus = property.getPropertyStatus();
		
		if(dealType == DealType.RENT && ( propertyStatus != PropertyStatus.RENTABLE && propertyStatus != PropertyStatus.SALEABLE_AND_RENTABLE)) {
			throw new DealException("Customer wants to  get the property in rent but it's not rentable.");
		}
		if(dealType == DealType.BUY && ( propertyStatus != PropertyStatus.SALEABLE && propertyStatus != PropertyStatus.SALEABLE_AND_RENTABLE)) {
			throw new DealException("Customer wants buy the property but it's not saleable.");
		}
		
		return true;
	}
	
	/**
	 * Edit or Negotiate Deal Offer
	 * 
	 * This field only should be accessible by the Customer, to edit his previous deal offer and pass it to the Broker for the
	 * property, then the deal with pending status will be added to the both Customer and Broker
	 * side, so the Customer can see/update the status of the deal and Broker can perform the action on the offer.
	 * 
	 * @param dealId: accepting one of the deal object from the list of a broker
	 * @return Deal: negotiated deal object
	 */
	@Override
	public Deal editDealOfferFromCustomerByDealId(Integer dealId, CustomerOffer customerOffer) {
		
		Deal deal = getDealbyID(dealId);
		if(deal.getDealStatus()!=DealStatus.PENDING) throw new DealException("The expected status for the Deal was to be: PENDING but instead found "+deal.getDealStatus()+".");
		
		Customer customer = cs.viewCustomerById(customerOffer.getCustomerId());
		if(deal.getCustomer().equals(customer)) throw new DealException("No Deal with Id: "+deal.getDealid()+" is related to Customer with Id: "+customer.getUserId());
			
		Broker broker = bs.viewBrokerById(customerOffer.getBrokerId());
		if(deal.getBroker().equals(broker)) throw new DealException("No Deal with Id: "+deal.getDealid()+" is related to Broker with Id: "+broker.getUserId());
	
		Property property = bs.getBrokerPropertyById(broker.getUserId(), customerOffer.getPropertyId());
		if(!deal.getProperty().equals(property)) throw new DealException("No Deal with Id: "+deal.getDealid()+" contains Property with Id: "+property.getPropertyId());
		if(!property.getIsAvailable()) throw new DealException("Property not avaliable for sale or rent");
		
		isPropertySuitableForDeal(property, customerOffer.getDealType());
		
		if(customerOffer.getDealCost()<0) throw new DealException("Deal cost cannot be in negative.");
		
		if(customerOffer.getDealType() == null) throw new DealException("Deal type undefined! Either it could be Buy or Rent.");
		
		if(customerOffer.getDealType()==DealType.RENT) {
			
		        if(!(customerOffer.getStartPeriod() != null && customerOffer.getEndPeriod() != null)) throw new DealException("Start and end period must be specified for rent deals");
		        
		        if(customerOffer.getStartPeriod().isBefore(LocalDate.now())) throw new DealException("The start period for renting the property is in the past, but it must be in the future.");
		        
		        if(customerOffer.getEndPeriod().isBefore(customerOffer.getStartPeriod())) throw new DealException("The end period for renting the property is before the start period, but it must be after the start period.");
		        
		        pss.checkIsPropertyScheduledByPropertyIdAndBetweenStartDateAndEndDate(property.getPropertyId(), customerOffer.getStartPeriod(), customerOffer.getEndPeriod());
		}
		
		deal.setDealCost(customerOffer.getDealCost());
		deal.setDealType(customerOffer.getDealType());
		if(deal.getDealType()==DealType.RENT) {
			deal.setRentStartPeriod(customerOffer.getStartPeriod());
			deal.setRentEndPeriod(customerOffer.getEndPeriod());
		}else {
			deal.setRentStartPeriod(null);
			deal.setRentEndPeriod(null);
		}
		
		//--> customer making/(editing) a deal means he agrees to the deal, but wait for the broker's response
		deal.setCustomerAgree(true);
		deal.setBrokerAgree(false);
		
		deal.setDealStatus(DealStatus.PENDING);
		deal.setDealDateTime(LocalDateTime.now());
		
		Deal updatedDeal = dr.save(deal);
		
		bs.editBrokerDealById(broker.getUserId(), deal);
		cs.editCustomerDealById(customer.getUserId(), deal);
		
		//notify the broker "Customer updated the deal offer"
		String notificationMessage = customer.getCustomerName()+" updated his deal offer.";
		BrokerNotification brokerNotification = new BrokerNotification(broker.getUserId(), deal.getDealid(), LocalDateTime.now(), notificationMessage);
		
		bs.sendNotificationToBrokerAboutDeal(broker.getUserId(), brokerNotification);
		
		return updatedDeal;
		
	}
	
	@Override
	public Deal acceptDealForCustomer(Integer dealId, Integer customerId) {
		Deal deal = getDealbyID(dealId);
		if(deal.getDealStatus()!=DealStatus.PENDING) throw new DealException("The expected status for the Deal was to be PENDING but instead found "+deal.getDealStatus()+".");

		Customer customer = deal.getCustomer();

		if(customer.getUserId() != customerId) throw new DealException("Deal with Id "+dealId+" isn't related to Customer with Id "+customerId);
		
		Broker broker = deal.getBroker();
		
		Property property = deal.getProperty();
		
		isPropertySuitableForDeal(property, deal.getDealType());
		
		deal.setDealStatus(DealStatus.PAYMENT_PENDING);
		
		//--> Customer accepting straight to broker's offer, means both agree
		deal.setCustomerAgree(true);
		deal.setBrokerAgree(true);
		
		Bill bill = new Bill(deal);
		deal.setBill(bill);
		
		Deal updatedDeal = dr.save(deal);
		
		bs.editBrokerDealById(broker.getUserId(), deal);
		cs.editCustomerDealById(customer.getUserId(), deal);
		
		//--> notify Broker "deal got accepted by the Customer"
		String notificationMessage = customer.getCustomerName()+" accepted your deal offer.";
		BrokerNotification brokerNotification = new BrokerNotification(broker.getUserId(), deal.getDealid(), LocalDateTime.now(), notificationMessage);
		
		bs.sendNotificationToBrokerAboutDeal(broker.getUserId(), brokerNotification);
		
		return updatedDeal;
	}
	
	@Override
	public Deal rejectDealForCustomer(Integer dealId, Integer customerId) {
		Deal deal = getDealbyID(dealId);
		if(deal.getDealStatus()!=DealStatus.PENDING) throw new DealException("The expected status for the Deal was to be: PENDING but instead found "+deal.getDealStatus()+".");

		Customer customer = deal.getCustomer();
		if(customer.getUserId() != customerId) throw new DealException("Deal with Id "+dealId+" isn't related to Customer with Id "+customerId);
		
		Broker broker = deal.getBroker();
		
		Property property = deal.getProperty();
		
		if(!property.getIsAvailable()) throw new DealException("Property not avaliable for sale or rent");
		
		isPropertySuitableForDeal(property, deal.getDealType());
		
		deal.setDealStatus(DealStatus.REJECTED);
		
		//--> Customer denying straight, means both disagree
		deal.setCustomerAgree(false);
		deal.setBrokerAgree(false);
		
		Deal updatedDeal = dr.save(deal);
		
		bs.editBrokerDealById(broker.getUserId(), deal);
		cs.editCustomerDealById(customer.getUserId(), deal);
		
		//--> notify customer "deal got rejected by the broker"
		String notificationMessage = customer.getCustomerName()+" rejected your deal offer.";
		BrokerNotification brokerNotification = new BrokerNotification(broker.getUserId(), deal.getDealid(), LocalDateTime.now(), notificationMessage);
		
		bs.sendNotificationToBrokerAboutDeal(broker.getUserId(), brokerNotification);
		
		return updatedDeal;
	}
	
	@Override
	public Deal deleteDealOfferForCustomer(Integer dealId, Integer customerId) {
		
		Deal deal = getDealbyID(dealId);
		if(deal.getDealStatus()==DealStatus.FULFILLED) throw new DealException("FULFILLED deals cannot be deleted");

		Customer customer = deal.getCustomer();
		if(customer.getUserId() != customerId) throw new DealException("Deal with Id "+dealId+" isn't related to Customer with Id "+customerId);
		
		Broker broker = deal.getBroker();
		
		Property property = deal.getProperty();
		
		if(!property.getIsAvailable()) throw new DealException("Property not avaliable for sale or rest");
				
		//delete deal from Customer and Broker side both
		
		bs.deleteBrokerDealById(broker.getUserId(), dealId);
		cs.deleteCustomerDealById(customer.getUserId(), dealId);
		
		//--> notify customer "deal got rejected by the broker"
		String notificationMessage = customer.getCustomerName()+" deleted his deal offer.";
		BrokerNotification brokerNotification = new BrokerNotification(broker.getUserId(), deal.getDealid(), LocalDateTime.now(), notificationMessage);
		
		bs.sendNotificationToBrokerAboutDeal(broker.getUserId(), brokerNotification);
		
		dr.deleteById(dealId);
		
		return deal;
		
	}
	
	
	/**
	 * Edit or Negotiate Deal Offer
	 * 
	 * This field only should be accessible by the Broker, to pass his offer to the Customer for the
	 * property, then the deal with pending status will be added to the both Customer and Broker
	 * side, so the Customer can see/update the status of the deal and Broker can perform the action on the offer.
	 * 
	 * @param BrokerOffer: accepting one of the deal object from the list of a broker
	 * @return Deal: negotiated deal object
	 */
	@Override
	public Deal setDealOfferFromBroker(BrokerOffer brokerOffer) {
		

		Deal deal = dr.findById(brokerOffer.getDealId()).orElseThrow(()-> new DealException("No Deal available with Id: "+brokerOffer.getDealId()));
		if(deal.getDealStatus() != DealStatus.PENDING) throw new DealException("Deal status was expected to be PENDING but found "+deal.getDealStatus());

		
		Broker broker = bs.viewBrokerById(brokerOffer.getBrokerId());
		if(!deal.getBroker().equals(broker)) throw new DealException("No Deal with Id: "+deal.getDealid()+" is related to Broker with Id: "+broker.getUserId());
		
		Customer customer = cs.viewCustomerById(brokerOffer.getPropertyId());
		if(!deal.getCustomer().equals(customer)) throw new DealException("No Deal with Id: "+deal.getDealid()+" is related to Customer with Id: "+customer.getUserId());
		
		Property property = bs.getBrokerPropertyById(broker.getUserId(), brokerOffer.getPropertyId());;
		if(!deal.getProperty().equals(property)) throw new DealException("No Deal with Id: "+deal.getDealid()+" contains Property with Id: "+property.getPropertyId());
		if(!property.getIsAvailable()) throw new DealException("Property not avaliable for sale or rent");
		
		isPropertySuitableForDeal(property, deal.getDealType());
		
		if(brokerOffer.getDealCost()<0) throw new DealException("Deal cost cannot be in negative.");
		
		deal.setDealDateTime(LocalDateTime.now());
		deal.setDealCost(brokerOffer.getDealCost());
		
		//--> Broker offering a deal means he agrees to the deal, but wait for the customer's response
		deal.setCustomerAgree(false);
		deal.setBrokerAgree(true);
		
		Deal updatedDeal = dr.save(deal);
		
		bs.editBrokerDealById(broker.getUserId(), deal);	
		cs.editCustomerDealById(customer.getUserId(), deal);
		
		// notify customer
		String notificationMessage = broker.getBrokerName()+" offering you a deal.";
		CustomerNotification customerNotification = new CustomerNotification(customer.getUserId(), deal.getDealid(), LocalDateTime.now(), notificationMessage);
		
		cs.sendNotificationToCustomerAboutDeal(customer.getUserId(), customerNotification);
		
		return updatedDeal;

	}
	
	
	@Override
	public Deal approveDealForBroker(Integer dealId, Integer brokerId)throws  DealException{
	
		Deal deal = getDealbyID(dealId);
		if(deal.getDealStatus()!=DealStatus.PENDING) throw new DealException("The expected status for the Deal was to be: PENDING but instead found "+deal.getDealStatus()+".");
		
		Broker broker = bs.viewBrokerById(brokerId);
		if(!deal.getBroker().equals(broker)) throw new DealException("No Deal with Id: "+deal.getDealid()+" is related to Broker with Id: "+broker.getUserId());
		
		Customer customer = deal.getCustomer();
				
		Property property = deal.getProperty();
		if(!property.getIsAvailable()) throw new DealException("Property not avaliable for sale or rest");
		isPropertySuitableForDeal(property, deal.getDealType());
		pss.checkIsPropertyScheduledByPropertyIdAndBetweenStartDateAndEndDate(property.getPropertyId(), deal.getRentStartPeriod(), deal.getRentEndPeriod());
		
		deal.setDealStatus(DealStatus.PAYMENT_PENDING);
		
		//--> broker offering a deal means he agrees to the deal, but wait for the customer's response
		deal.setCustomerAgree(true);
		deal.setBrokerAgree(true);
		
		Bill bill = new Bill(deal);
		deal.setBill(bill);
		
		Deal updatedDeal = dr.save(deal);
		
		bs.editBrokerDealById(broker.getUserId(), deal);
		cs.editCustomerDealById(customer.getUserId(), deal);
		
		//--> notify the customer "deal got accepted proceed to pay."
		String notificationMessage = broker.getBrokerName()+" accepted your deal, now proceed to payment.";
		CustomerNotification customerNotification = new CustomerNotification(customer.getUserId(), deal.getDealid(), LocalDateTime.now(), notificationMessage);
		
		cs.sendNotificationToCustomerAboutDeal(customer.getUserId(), customerNotification);
		
		return updatedDeal;
		
	}
	
	@Override
	public Deal abandonedDealForBroker(Integer dealId, Integer brokerId) throws DealException {
		Deal deal = getDealbyID(dealId);
		if(deal.getDealStatus()!=DealStatus.PENDING) throw new DealException("The expected status for the Deal was to be: PENDING but instead found "+deal.getDealStatus()+".");
		
		Broker broker = deal.getBroker();
		
		if(broker.getUserId() != brokerId) throw new DealException("Deal with Id "+dealId+" isn't related to Broker with Id "+brokerId);
		
		Customer customer = deal.getCustomer();
		
		Property property = deal.getProperty();
		
		if(!property.getIsAvailable()) throw new DealException("Property not avaliable for sale or rest");
		
		isPropertySuitableForDeal(property, deal.getDealType());
		
		deal.setDealStatus(DealStatus.REJECTED);
		
		//--> broker denying straight, means both disagree
		deal.setCustomerAgree(false);
		deal.setBrokerAgree(false);
		
		Deal updatedDeal = dr.save(deal);
		
		bs.editBrokerDealById(broker.getUserId(), deal);
		cs.editCustomerDealById(customer.getUserId(), deal);
		
		//--> notify customer "deal got rejected by the broker"
		
		String notificationMessage = broker.getBrokerName()+" rejected your deal offer.";
		CustomerNotification customerNotification = new CustomerNotification(customer.getUserId(), deal.getDealid(), LocalDateTime.now(), notificationMessage);
		cs.sendNotificationToCustomerAboutDeal(customer.getUserId(), customerNotification);
		
		return updatedDeal;
	}

	@Override
	public PaymentReceipt updateBillStatusInDealById(Deal deal,Bill bill) {
		
		deal.setBill(bill);
		
		deal = dr.save(deal);
		
		return new PaymentReceipt(deal, LocalDateTime.now());
		
	}
	
	@Override
	public Deal finaliseDealAfterPayment(PaymentReceipt paymentReceipt) {
		
		Deal deal = paymentReceipt.getDeal();
		
		if(deal.getBill() == null) throw new DealException("Bill is not present in Deal object at the time of finalizing the deal.");
		if(deal.getBill().getBillStatus()!=BillStatus.PAID) throw new DealException("Bill was expected to be paid at the of finalizing the deal.");
		if(deal.getCustomer()==null) throw new DealException("Customer can't be Null inside Deal at the time of finalizing the deal.");
		if(deal.getBroker()==null) throw new DealException("Broker can't be Null inside Deal at the time of finalizing the deal.");
		if(deal.getProperty()==null) throw new DealException("Property can't be Null inside Deal at the time of finalizing the deal.");
		
		Property property = ps.viewPropertyById(deal.getProperty().getPropertyId());
		
		if(!property.getIsAvailable()) throw new DealException("Property not avaliable for sale or rent");
		
		isPropertySuitableForDeal(property, deal.getDealType());
		
		if(deal.getDealType() == DealType.RENT) {
			
			ps.rentPropertyById(deal);
			
		}else {
			
			ps.buyPropertyById(deal);
			
		}
		//notify both customer and broker
		Broker broker = deal.getBroker();
		Customer customer = deal.getCustomer();
		
		
		
		String notificationMessageForBroker = "Deal successful between you and "+customer.getCustomerName();
		BrokerNotification brokerNotification = new BrokerNotification(broker.getUserId(), deal.getDealid(), LocalDateTime.now(), notificationMessageForBroker);
		bs.sendNotificationToBrokerAboutDeal(broker.getUserId(), brokerNotification);
		
		String notificationMessageForCustomer = "Deal successful between you and "+broker.getBrokerName();
		CustomerNotification customerNotification = new CustomerNotification(customer.getUserId(), deal.getDealid(), LocalDateTime.now(), notificationMessageForCustomer);
		cs.sendNotificationToCustomerAboutDeal(customer.getUserId(), customerNotification);
		
		deal.setDealStatus(DealStatus.FULFILLED);
		
		Deal updatedDeal = dr.save(deal);
		
		bs.editBrokerDealById(broker.getUserId(), deal);
		cs.editCustomerDealById(customer.getUserId(), deal);
		
		return updatedDeal;
	}

}



