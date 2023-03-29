package com.brokerApplication.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.brokerApplication.entities.CustomerDummy;
import com.brokerApplication.entities.User;
import com.brokerApplication.exceptions.UserException;
//import com.brokerApplication.repositorys.CustomerDummyRepo;
import com.brokerApplication.repositorys.UserRepo;

import jakarta.annotation.PostConstruct;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepo ur;
//	@Autowired
//	CustomerDummyRepo cdr;
	
//	@PostConstruct
//	public void init() {
//		CustomerDummy cd=new CustomerDummy(createUser(new User("lokesh","lokesh@gmail.com","123456789","customer","hyd","12345")),"lokesh Paramkusham");
//		cdr.save(cd);
//	}
	
	
	@Override
	public User createUser(User user) throws UserException {
		User existingUser= ur.findByusername(user.getUsername());		
		if(existingUser != null) throw new UserException("provided Username already exist !");
		return ur.save(user);
	}

	@Override
	public User deleteUser(Integer id) throws UserException {
		Optional<User> existingUser= ur.findById(id);
		if(!existingUser.isPresent()) throw new UserException("Username not exist !");
		ur.delete(existingUser.get());
		return existingUser.get();
	}

	@Override
	public User UpdateUser(Integer id, User user) throws UserException {
		Optional<User> existingUser= ur.findById(id);
		if(!existingUser.isPresent()) throw new UserException("Username not exist !");
		return ur.save(user);
	}



}
