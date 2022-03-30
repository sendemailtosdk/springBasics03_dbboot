package com.springBasics.dataService;

import java.util.List;

//import javax.management.Query;
///import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springBasics.entities.Vendor;



public interface If_VendorPersistance extends JpaRepository<Vendor, String>{
   
public List<Vendor> findByCompanyName(String companyName);
   

@Query(nativeQuery=true,value="SELECT * FROM public.vendor where lower(GST_NO) like %?1% ")
public List<Vendor> lookUpVendorByGST(String GSTNo);

 }
