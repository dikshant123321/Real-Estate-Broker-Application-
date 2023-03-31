package com.brokerApplication.services;

import javax.security.auth.login.LoginException;

import com.brokerApplication.entities.LoggedSession;
import com.brokerApplication.entities.LoginCredential;

public interface UserLoginService {
	public LoggedSession logIntoAccount(LoginCredential dto) throws LoginException;

	public String logOutFromAccount(String key) throws LoginException;
}
