package com.brokerApplication.services;

import com.brokerApplication.entities.User;
import com.brokerApplication.exceptions.UserException;

public interface UserService {
	public User createUser(User user) throws UserException;
	public User deleteUser(Integer id) throws UserException;
	public User UpdateUser(Integer id,User user) throws UserException;
}
