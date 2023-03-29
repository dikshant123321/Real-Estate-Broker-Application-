package com.brokerApplication.services;

import javax.security.auth.login.LoginException;

import com.brokerApplication.entities.LoginCredential;

public interface UserLoginService {
	public String logIntoAccount(LoginCredential dto) throws LoginException;

	public String logOutFromAccount(String key) throws LoginException;
}
