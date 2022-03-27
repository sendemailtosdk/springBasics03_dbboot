package com.springBasics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springBasics.dataService.addressDataService;
import com.springBasics.entities.Address;

@RestController
public class AddressController {

@Autowired
addressDataService ads;	

@RequestMapping("/address")
public List<Address> readAllAddress(){
	
return ads.readAllAddress();	
}

@RequestMapping("/address/{addressID}")
public Address getAddressByID(@PathVariable("addressID") String id){
	
return ads.getAddressByID(id);	
}

@PostMapping("/address")
public Address createAddress(@RequestBody Address payload ) {
	
return ads.createAddress(payload);
	
}


@RequestMapping(method = RequestMethod.PUT,value = "/address" )
public Address changeAddress(@RequestBody Address payload){	
return ads.changeAddress(payload);	
}


}
