package com.brokerApplication.webApplicationController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.brokerApplication.entities.Broker;
import com.brokerApplication.webApplicationServices.BrokerWebServices;

@Controller
public class BrokerControllerWeb {
	private BrokerWebServices brokerServices;
	public BrokerControllerWeb(BrokerWebServices brokerServices) {
		super();
		this.brokerServices = brokerServices;
	}
	
	@GetMapping("/mybrokers")
	public String listBrokers(Model model) {
		model.addAttribute("brokers",brokerServices.getAllBrokers());
		return "mybrokers";
	}
	
	@GetMapping("/mybrokers/new")
	public String createBrokerForm(Model model) {
		Broker broker = new Broker();
		model.addAttribute("broker", broker);
		return "createbroker";
	}
	
	@PostMapping("/mybrokers")
	public String makeBroker(@ModelAttribute("broker") Broker broker) {
		System.out.println(broker);
		brokerServices.saveBr(broker);
		return "redirect:/mybrokers";
	}
	
	@GetMapping("/mybrokers/show/{id}")
	public String showproper(@PathVariable Integer id,Model model) {
		model.addAttribute("properties", brokerServices.getAllProperties(id));
		return "brokersproperties";
	}
}
