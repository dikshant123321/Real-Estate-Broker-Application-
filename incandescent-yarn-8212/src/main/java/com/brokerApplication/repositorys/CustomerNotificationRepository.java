package com.brokerApplication.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brokerApplication.entities.CustomerNotification;

@Repository
public interface CustomerNotificationRepository extends JpaRepository<CustomerNotification, Integer>{

	
	
}
