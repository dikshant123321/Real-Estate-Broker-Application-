package com.brokerApplication.controllers;

import java.util.Map;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.brokerApplication.entities.LoginCredential;
import com.brokerApplication.exceptions.AuthorizationException;
import com.brokerApplication.services.AuthorizationService;
import com.brokerApplication.services.UserLoginService;

@RestController
public class SingUpAndSingInController {
	@Autowired
	UserLoginService uls;
	@Autowired
	AuthorizationService as; 
	
	@PostMapping("/EstateExplorer/Login")
	public ResponseEntity<String> signIn(@RequestBody LoginCredential logced) throws LoginException{
		return new ResponseEntity<String>(uls.logIntoAccount(logced),HttpStatus.OK);
	}
	
	@PostMapping("/EstateExplorer/Logout")
	public ResponseEntity<String> signIn(@RequestBody String key) throws LoginException{
		return new ResponseEntity<String>(uls.logOutFromAccount(key),HttpStatus.OK);
	}
	
	@GetMapping("/CUSTOMER/{id}")
	public ResponseEntity<String> welcome(@PathVariable Integer id,@RequestHeader ("Auth") String key) throws LoginException, AuthorizationException{
		String str=as.Auth(id,key)?"hii Customer":null;
		return new ResponseEntity<String>(str,HttpStatus.OK);
	}

}