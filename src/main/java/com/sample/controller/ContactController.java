package com.sample.controller;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Iterables;
import com.sample.model.Contact;
import com.sample.repository.ContactRespository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

@RestController
@RequestMapping("contacts")
public class ContactController {
	private static final Logger log = LoggerFactory.getLogger(ContactController.class);

	@Autowired
	ContactRespository repository;

	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<List<Contact>> searchContacts(@RequestParam(value="state", required=false, name="state") String state,
			@RequestParam(value="city", required=false, name="city") String city) {
		
		
		log.debug("parametersparametersparameters state="+state);
		log.debug("parametersparametersparameters city="+city);
		
		List<Contact> contacts = Collections.emptyList();
		
		if(StringUtils.isEmpty(city) && StringUtils.isEmpty(state)){
			  contacts = (List<Contact>) repository.findAll();
		}else{
			if(StringUtils.isNotBlank(state)){
			  contacts.addAll((List<Contact>) repository.findByState(state.toUpperCase()));
			}
			if(StringUtils.isNotBlank(city)){
			contacts.addAll((List<Contact>) repository.findByCity(city.toUpperCase()));
			}
		}

		if (Iterables.size(contacts) == 0) {
			return new ResponseEntity<List<Contact>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Contact> getContact(@PathVariable("id") String id) {

		if (StringUtils.isNumeric(id)) {
			Contact contact = repository.findOne(Long.valueOf(id));
			if (contact != null) {
				return new ResponseEntity<Contact>(contact, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Contact> deleteContact(@PathVariable("id") String id) {
		if (StringUtils.isNumeric(id)) {
			Contact contact = repository.findOne(Long.valueOf(id));
			if (contact != null) {
				repository.delete(contact.getId());
				return new ResponseEntity<Contact>(HttpStatus.NO_CONTENT);
			}
		}
		return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<?> createContact(@Valid @RequestBody Contact contact) {
		try {

			contact = repository.save(contact);
			log.debug("Creating Contact: ", contact);
			return new ResponseEntity<Contact>(contact, HttpStatus.CREATED);
 
		} catch (Exception e) {
			log.error("Exception: ", e);
		}
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);

	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updateContact(@Valid @RequestBody Contact contact) {

		try {
			Contact contactOriginal = repository.findOne(contact.getId());
			if (contactOriginal != null) {
				contact = repository.save(contact);
				log.debug("Updated Contact: ", contact);
				return new ResponseEntity<Contact>(contact, HttpStatus.OK);
			}
		
		} catch (Exception e) {
			log.error("Exception: ", e);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
