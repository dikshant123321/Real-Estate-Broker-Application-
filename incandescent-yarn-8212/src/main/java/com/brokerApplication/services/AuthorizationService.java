package com.brokerApplication.services;

import com.brokerApplication.exceptions.AuthorizationException;

public interface AuthorizationService {
	public boolean Auth(Integer id,String key)throws AuthorizationException;
}
