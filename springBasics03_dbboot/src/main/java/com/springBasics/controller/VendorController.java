package com.springBasics.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springBasics.dataService.vendorDataService;
import com.springBasics.entities.Vendor;

@RestController
public class VendorController {

	@Autowired
	vendorDataService vds;

	@RequestMapping("/vendor/{vendorCode}")
	public Vendor getVendorByCode(@PathVariable("vendorCode") Long code) {
        Optional<Vendor> searchResult = vds.getSingleVendorByID(code);
        if(!searchResult.isPresent()) {
        
        	return new Vendor();
        	
        }
		return searchResult.get();

	}

	@RequestMapping("/vendor")
	public List<Vendor> getVendors() {
		return vds.readAllVendors();

	}
	
	// test using Localhost:8080/vendor/search?company=SAP
	@RequestMapping("/vendor/search")
	public List<Vendor> getVendors(@RequestParam String companyName) {
		return vds.getVendorByCompanyName(companyName);

	}
	
	@RequestMapping("/vendor/lookup")
	public List<Vendor> lookupVendorByGST(@RequestParam String gstNo) {
		return vds.lookupVendorByGST(gstNo);

	}
	
	@RequestMapping("/vendor/lookupGST/{gstNo}")
	public List<Vendor> searchVendorByGST(@PathVariable("gstNo") String gstNo) {
		return vds.lookupVendorByGST(gstNo);

	}

	@PostMapping("/vendorCreate")
	public Vendor createVendor(@RequestBody Vendor myPostBody) {

		return vds.createVendor(myPostBody);

	}

	@PutMapping("/vendorUpdate")
	@RequestMapping(method = RequestMethod.PUT, value = "/vendorUpdate")
	public Vendor createUpdate(@RequestBody Vendor myPutBody) {
		return vds.changeVendor(myPutBody);

	}

}
