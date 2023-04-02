package com.brokerApplication.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brokerApplication.entities.Bill;

@Repository
public interface BillingRepository extends JpaRepository<Bill, Integer>{

}
