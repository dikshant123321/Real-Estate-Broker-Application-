package com.brokerApplication.WebContainer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brokerApplication.entities.Broker;
import com.brokerApplication.services.BrokerServices;

@Controller
public class BrokerWebController {

	@Autowired
	private BrokerServices brokerServices;
	
	@GetMapping("/")
    public String view(Model model) {
        List<Broker> listBroker = brokerServices.listAllBrokers();
        model.addAttribute("brokers", listBroker);
        return "index";
    }
 
    @GetMapping("/addBroker")
    public String add(Model model) {
        model.addAttribute("newBroker", new Broker());
        return "new";
    }
	
    @PostMapping("/saveBroker")
    public String save(@ModelAttribute("newBroker") Broker broker) {
        brokerServices.addBroker(broker);
        return "redirect:/";
    }
 
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Integer id) {
        brokerServices.removeBrokerById(id);
        return "redirect:/";
    }
	
	
//    @RequestMapping("/edit/{id}")
//    public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
//        ModelAndView mav = new ModelAndView("new");
//        Broker newBroker = 
//        mav.addObject("student", std);
//        return mav;
//        
//    }
//	
//	
//	
//	
//	
//	@PostMapping("/brokers")
//	public ResponseEntity<Broker> registerBrokerHandler(@RequestBody Broker broker){
//		
//		Broker newBroker = brokerServices.addBroker(broker);
//		return new ResponseEntity<>(newBroker, HttpStatus.CREATED);
//	}
//	
//	@GetMapping("/brokers")
//	public ResponseEntity<List<Broker>> getAllBrokersHandler(){
//		List<Broker> brokers= brokerServices.listAllBrokers();
//		return new ResponseEntity<>(brokers,HttpStatus.FOUND);
//	}
//	
//	
//	@GetMapping("/brokers/{id}")
//	public ResponseEntity<Broker> getBrokerByIdHandler(@PathVariable Integer id){
//		Broker broker = brokerServices.viewBrokerById(id);
//		return new ResponseEntity<>(broker,HttpStatus.FOUND);
//	}
//	
//	@DeleteMapping("/brokers/{id}")
//	public ResponseEntity<Broker> deleteBrokerByIdHandler(@PathVariable Integer id){
//		Broker broker = brokerServices.removeBrokerById(id);
//		return new ResponseEntity<>(broker,HttpStatus.OK);
//	}
//	
//	@PutMapping("/brokers/{Id}")
//	@ResponseBody
//	public ResponseEntity<Broker> updateBrokerByIdHandler(@PathVariable Integer id, Broker broker){
//		Broker newbroker = brokerServices.editBroker(broker);
//		return new ResponseEntity<>(newbroker,HttpStatus.OK);
//	}
}
