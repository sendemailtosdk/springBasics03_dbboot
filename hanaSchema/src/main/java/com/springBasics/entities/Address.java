package com.springBasics.entities;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ADDRESS")
public class Address {
@Id
//@GeneratedValue(strategy = GenerationType.AUTO)
@GeneratedValue(generator = "uuid2")
@GenericGenerator(name = "uuid2",strategy = "org.hibernate.id.UUIDGenerator")
@Column(nullable = false,name = "ID")
private String addressId;
@Column(name = "TYPE")
private String addressType;
@Column(name = "STREET")
private String street;
@Column(name = "CITY")
private String city;
@Column(name = "REGION")
private String region;
@Column(name = "COUNTRY")
private String country;



public Address( ) {
	
	
}

public Address(String addressId, String addressType, String street, String city, String region, String country) {
	super();
	this.addressId = addressId;
	this.addressType = addressType;
	this.street = street;
	this.city = city;
	this.region = region;
	this.country = country;
}
public String getAddressId() {
	return addressId;
}
public void setAddressId(String addressId) {
	this.addressId = addressId;
}
public String getAddressType() {
	return addressType;
}
public void setAddressType(String addressType) {
	this.addressType = addressType;
}
public String getStreet() {
	return street;
}
public void setStreet(String street) {
	this.street = street;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getRegion() {
	return region;
}
public void setRegion(String region) {
	this.region = region;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}


}
