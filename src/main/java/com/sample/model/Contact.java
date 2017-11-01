package com.sample.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
public class Contact implements Serializable {

	private static final long serialVersionUID = -3490412181209276754L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CONTACT_ID")
	private long id;
	
	@NotNull
	@Size(min=2, max=50)
	private String name;
	
	private String company;
	private String image;
	
	@Email
	@NotEmpty(message = "Please enter your email addresss.")
	@Pattern(regexp="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")
	private String email;
	

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date birthDate;
	
	@Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}")
	private String phoneNumber;
	
	private String address;
	private String city;
	
	@Size(min=2, max=2)
	private String state;
	
	@Pattern(regexp="^\\d{5}(?:[-\\s]\\d{4})?$")
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


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(final Date birthDate) {
		this.birthDate = birthDate;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber != null ? phoneNumber.replaceAll("\\s", ""):phoneNumber;
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

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + " ]";
	}

}
