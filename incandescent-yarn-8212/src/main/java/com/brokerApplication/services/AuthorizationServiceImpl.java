package com.brokerApplication.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brokerApplication.entities.LoggedSession;
import com.brokerApplication.exceptions.AuthorizationException;
import com.brokerApplication.repositorys.LoggedSessionRepo;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
	
	@Autowired
	LoggedSessionRepo lsr;
	
	@Override
	public boolean Auth(Integer id, String key)throws AuthorizationException{
		Optional<LoggedSession> op1=lsr.findById(id);
		if(!op1.isPresent()) throw new AuthorizationException("Authorization Error (User not logedin)");
		if(!op1.get().getUniquekey().equals(key))throw new AuthorizationException("Authorization Error (Enter correct Key)");
		return true;
	}

}
