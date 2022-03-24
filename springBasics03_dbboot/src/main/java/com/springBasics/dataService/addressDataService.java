package com.springBasics.dataService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springBasics.entities.Address;

@Component
public class addressDataService {

	@Autowired
	If_AddressPersistance adr;
	
	public List<Address> readAllAddress() {

		return adr.findAll();	
		
	}
	
	public Address getAddressByID(Long id) {
		Optional<Address> myaddress = adr.findById(id);
		if(!myaddress.isPresent()) {			
			return new Address( );
		} 
	return myaddress.get();
	
		
	}
	
	
	public Address changeAddress(Address omAddress) {
		Optional<Address> myaddress = adr.findById(omAddress.getAddressId());
		if(!myaddress.isPresent()) {			
			return new Address( );
		} 
		return adr.save(omAddress);
		
	}
	
	public Address createAddress(Address oAddress) {
		return adr.save(oAddress);
		
	}
	
	
}
