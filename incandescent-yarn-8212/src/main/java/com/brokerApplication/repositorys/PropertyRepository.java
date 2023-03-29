package com.brokerApplication.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brokerApplication.entities.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer>{

}
