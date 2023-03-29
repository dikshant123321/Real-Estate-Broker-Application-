//package com.brokerApplication.services;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.apache.el.stream.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.brokerApplication.entities.Deal;
//import com.brokerApplication.exceptions.DealException;
//import com.brokerApplication.repositorys.DealRepo;
//
//
//@Service
//public class DealServiceImpl implements DealService{
//	@Autowired
//	PropertyService ps;
//	@Autowired
//	CustomerService cs;
//	@Autowired
//	DealRepo dr;
//	@Autowired
//	PropertyRepo pr;
//	@Autowired
//	BrokerRepo br;
//	
//	@Override
//	public Deal addDeal(Integer pid,Integer cid) throws DealException {
//		Property proper=ps.viewProperty(pid);
//		Customer custo=cs.viewCustomer(cid);
//		if(proper==null) throw new DealException("Customer not found");
//		if(custo==null) throw new DealException("Property not found");
//		if(!proper.getStatus()) throw DealException("Property not avaliable for sale");
//		proper.setStatus(false);
//		pr.save(proper);
//		return dr.save(new Deal(LocalDate.now(),p.getOfferCost(),proper.getOfferType(),false,proper,custo));
//	}
//
//	@Override
//	public List<Deal> getAllDeals() {
//		List<Deal> list= dr.findAll();
//		if(list.isEmpty()) throw new DealException("No Deals Placed");
//		return list;
//	}
//
//	@Override
//	public String purchaseDeal(Integer did,Integer bid) throws DealException {
//		Optional<Deal> op1=dr.findById(did);
//		if(!op1.isPresent()) throw DealException("chose proper deal");
//		if(!op1.get().getProperty().getBroker().getBroId()==op2.get().getBroId())throw DealException("choose correct property ");
//		deal.setDealStatus(true);
//		return "Deal Done";
//	}
//
//	@Override
//	public String AbandonedDeal(Integer did,Integer bid) throws DealException {
//		Optional<Deal> op1=dr.findById(did);
//		Optional<Broker> op2=br.findById(bid);
//		if(!op1.isPresent()) throw DealException("choose proper deal");
//		if(!op1.get().getProperty().getBroker().getBroId()==op2.get().getBroId())throw DealException("choose correct property ");
//		dr.delete(op1.get());
//		return "Deal cancelled";
//	}
//
//
//	
//}
//
//
//
