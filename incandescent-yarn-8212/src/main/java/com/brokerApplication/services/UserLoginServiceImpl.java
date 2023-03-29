package com.brokerApplication.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brokerApplication.entities.LoggedSession;
import com.brokerApplication.entities.LoginCredential;
import com.brokerApplication.entities.User;
import com.brokerApplication.repositorys.LoggedSessionRepo;
import com.brokerApplication.repositorys.UserRepo;


@Service
public class UserLoginServiceImpl implements UserLoginService {
	
	@Autowired
	UserRepo ur;
	
	@Autowired
	LoggedSessionRepo lsr;
	
	@Override
	public String logIntoAccount(LoginCredential dto) throws LoginException {
		User existingUser= ur.findByusername(dto.getUsername());		
		if(existingUser == null) throw new LoginException("Please Enter a valid Username");
		Optional<LoggedSession> validLoggedSessionOpt =  lsr.findById(existingUser.getUserId());
		if(validLoggedSessionOpt.isPresent()) throw new LoginException("User already Logged");
		if(existingUser.getPassword().equals(dto.getPassword())) {
			String key= generateRandomChars(16);
			LoggedSession currentUserSession = new LoggedSession(existingUser.getUserId(),key,LocalDateTime.now());
			lsr.save(currentUserSession);
			return currentUserSession.toString();
		}
		else throw new LoginException("Please Enter a valid password");
	}
	public String generateRandomChars(int length) {
		String candidateChars="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    for (int i = 0; i < length; i++) {
	        sb.append(candidateChars.charAt(random.nextInt(candidateChars
	                .length())));
	    }
	    return sb.toString();
	}
	@Override
	public String logOutFromAccount(String key) throws LoginException {
		System.out.println("hello"+key);
		LoggedSession validLoggedSession = lsr.findByuniquekey(key);
		if(validLoggedSession==null) throw new LoginException("User Not Logged ");
		lsr.delete(validLoggedSession);
		return "Logged Out !";
	}

}
