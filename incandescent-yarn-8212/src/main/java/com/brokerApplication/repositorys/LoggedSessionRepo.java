package com.brokerApplication.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brokerApplication.entities.LoggedSession;

@Repository
public interface LoggedSessionRepo extends JpaRepository<LoggedSession, Integer> {
	
	public LoggedSession findByuniquekey(String String);
	
}
