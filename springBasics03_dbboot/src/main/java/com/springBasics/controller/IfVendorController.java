package com.springBasics.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.springBasics.entities.Vendor;

@RepositoryRestResource(collectionResourceRel = "vendor",path = "newVendor" )
public interface IfVendorController extends JpaRepository<Vendor, Long> {

}
