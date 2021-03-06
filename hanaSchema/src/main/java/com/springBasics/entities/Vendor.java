package com.springBasics.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="VENDOR")
public class Vendor {
@Id
//@GeneratedValue(strategy=GenerationType.AUTO)
@GeneratedValue(generator = "uuid2")
@GenericGenerator(name = "uuid2",strategy = "org.hibernate.id.UUIDGenerator")
@Column(nullable=false,name = "ID")
public String id;
@Column(nullable=false,name = "COMPANY_NAME")
public String companyName;
@Column(nullable=false,name = "FIRST_NAME")
public String firstName;
@Column(nullable=false,name = "LAST_NAME")
public String lastName;
@Column(nullable=false,name = "WEBSITE")
public String website;
@Column(nullable=false,name = "EMAIL")
public String email;
@Column(nullable=false,name = "STATUS")
public String status;
@Column(nullable=false,name = "GST_NO")
public String gstNo;

@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
@JoinColumn(name="vendor",referencedColumnName = "ID")
private List<Address> addresses = new ArrayList<>();


public List<Address> getAddresses() {
	return addresses;
}


public Vendor() {
	
	
}



public Vendor(String id, String companyName, String firstName, String lastName, String website, String email,
		String status, String gstNo, List<Address> addresses) {
	super();
	this.id = id;
	this.companyName = companyName;
	this.firstName = firstName;
	this.lastName = lastName;
	this.website = website;
	this.email = email;
	this.status = status;
	this.gstNo = gstNo;
	this.addresses = addresses;
}





public void setAddresses(List<Address> addresses) {
	this.addresses = addresses;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getCompanyName() {
	return companyName;
}
public void setCompanyName(String companyName) {
	this.companyName = companyName;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getWebsite() {
	return website;
}
public void setWebsite(String website) {
	this.website = website;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getGstNo() {
	return gstNo;
}
public void setGstNo(String gstNo) {
	this.gstNo = gstNo;
}



}
