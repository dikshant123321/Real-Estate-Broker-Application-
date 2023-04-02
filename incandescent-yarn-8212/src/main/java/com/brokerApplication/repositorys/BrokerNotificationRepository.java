package com.brokerApplication.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brokerApplication.entities.BrokerNotification;

@Repository
public interface BrokerNotificationRepository extends JpaRepository<BrokerNotification, Integer>{

}
