package com.brokerApplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	@RequestMapping("/broker")
	public String broker(@RequestParam Integer userid,@RequestParam String key) {
		System.out.println(key);
		System.out.println(userid);
		return "Broker.html";//copy paste your code in Broker.html(kashish)
	}
	@RequestMapping("/customer")
	public String customer(@RequestParam Integer userid,@RequestParam String key) {
		System.out.println(key);
		System.out.println(userid);
		return "Customer.html";//copy paste your code in Customer.html(dikshant)
	}
	@RequestMapping("/EstateExplorer")
	public String t() {
		return "Home.html";
	}
	
	
}
