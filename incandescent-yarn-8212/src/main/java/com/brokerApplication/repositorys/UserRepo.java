package com.brokerApplication.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brokerApplication.entities.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	public User findByusername(String user);
}
