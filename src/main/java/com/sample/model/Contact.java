package com.sample.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;



@Entity
public class Contact implements Serializable {

	private static final long serialVersionUID = -3490412181209276754L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CONTACT_ID")
	private long id;
	
	@NotNull
	private String name;
	
	private String company;
	private String image;
	private String email;
	private LocalDate birthDate;
	private String phoneNumber;
	private String address;
	private String city;
	private String state;
	private String postalCode;
	private String country;


	protected Contact() {
	}


	public Contact(String name) {
		super();
		this.name = name;
	}


	public long getId() {
		return id;
	}


	public void setId(final long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(final String name) {
		this.name = name;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(final String company) {
		this.company = company;
	}


	public String getImage() {
		return image;
	}


	public void setImage(final String image) {
		this.image = image;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(final String email) {
		this.email =   email != null?email.toUpperCase():email;;
	}


	public LocalDate getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(final LocalDate birthDate) {
		this.birthDate = birthDate;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(final String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(final String city) {
		this.city = city!= null?city.toUpperCase():city;
	}


	public String getState() {
		return state;
	}


	public void setState(final String state) {
		this.state = state != null?state.toUpperCase():state;
	}


	public String getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(final String postalCode) {
		this.postalCode = postalCode;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(final String country) {
		this.country = country;
	}



}
