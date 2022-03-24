package com.springBasics.dataService;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springBasics.entities.Vendor;

@Component
public class vendorDataService {

@Autowired
public If_VendorPersistance vp;
	
	public List<Vendor> readAllVendors() {

		return vp.findAll();	
		
	}
	
	public Optional<Vendor> getSingleVendorByID(Long code) {

	return vp.findById(code);

		
	}
	
	public List<Vendor> getVendorByCompanyName(String companyName){
		
	return vp.findByCompanyName(companyName);	
	}
	
	public List<Vendor> lookupVendorByGST(String GSTNo){
		
	return vp.lookUpVendorByGST(GSTNo);	
	}
	
	
	public Vendor changeVendor(Vendor omVendor) {

		return vp.save(omVendor);
		
	}
	
	public Vendor createVendor(Vendor oVendor) {
		return vp.save(oVendor);
		
	}
	

}
