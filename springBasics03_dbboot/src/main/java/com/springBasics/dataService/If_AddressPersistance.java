package com.springBasics.dataService;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBasics.entities.Address;

public interface If_AddressPersistance extends JpaRepository<Address, Long> {

}
