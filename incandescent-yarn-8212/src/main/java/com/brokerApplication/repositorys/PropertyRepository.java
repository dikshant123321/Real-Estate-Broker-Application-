package com.brokerApplication.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brokerApplication.entities.Property;

public interface PropertyRepository extends JpaRepository<Property, Integer>{

}
