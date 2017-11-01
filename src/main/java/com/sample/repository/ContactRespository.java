package com.sample.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sample.model.Contact;

public interface ContactRespository extends CrudRepository<Contact, Long> {

	 List<Contact> findByEmail(String email);
	 List<Contact> findByPhoneNumber(String phoneNumber);
	 List<Contact> findByCity(String city);
	 List<Contact> findByState(String state);
}
