package com.sample.controller;

import java.io.IOException;
import java.util.List;

import org.h2.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Iterables;
import com.sample.model.Contact;
import com.sample.repository.ContactRespository;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
public class ContactController {
	private static final Logger log = LoggerFactory.getLogger(ContactController.class);

	@Autowired
	ContactRespository repository;

	@RequestMapping(path = "/contactst", method = RequestMethod.GET)
	public ResponseEntity<List<Contact>> getAllContacts() {
		List<Contact> contacts = (List<Contact>) repository.findAll();
		if (Iterables.size(contacts) == 0) {
			return new ResponseEntity<List<Contact>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
	}

	@RequestMapping(path = "/contact/{id}", method = RequestMethod.GET)
	public ResponseEntity<Contact> getContact(@PathVariable("id") String id) {

		if (StringUtils.isNumber(id)) {
			Contact contact = repository.findOne(Long.valueOf(id));
			if (contact != null) {
				return new ResponseEntity<Contact>(contact, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(path = "/contact/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Contact> deleteContact(@PathVariable("id") String id) {
		if (StringUtils.isNumber(id)) {
			Contact contact = repository.findOne(Long.valueOf(id));
			if (contact != null) {
				repository.delete(contact.getId());
				return new ResponseEntity<Contact>(HttpStatus.NO_CONTENT);
			}
		}
		return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/contact", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createContact(
			@RequestBody(required = true) String jsonString) {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			Contact contact = convertJsonToPoJo(jsonString, objectMapper);
			contact = repository.save(contact);
			return new ResponseEntity<Contact>(contact, HttpStatus.CREATED);
		} catch (JsonParseException e) {
			log.error("JsonParseException: ", e);
		} catch (JsonMappingException e) {
			
			log.error("JsonMappingException: ", e);
		} catch (IOException e) {
			log.error("IOException: ", e);
		}
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);

	}

	@RequestMapping(value = "/contact", 
			       method = RequestMethod.PUT, 
			       consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateHouse(
			@RequestBody(required = true) String jsonString) {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			Contact contact = convertJsonToPoJo(jsonString, objectMapper);
			Contact contactOriginal = repository.findOne(contact.getId());
			if (contactOriginal != null) {
				contact = repository.save(contact);
				return new ResponseEntity<Contact>(contact, HttpStatus.OK);
			}
		} catch (JsonParseException e) {
			log.error("JsonParseException: ", e);
		} catch (JsonMappingException e) {
			log.error("JsonMappingException: ", e);
		} catch (IOException e) {
			log.error("IOException: ", e);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

	}

	private Contact convertJsonToPoJo(final String jsonString,
			final ObjectMapper objectMapper) throws IOException, JsonParseException,
			JsonMappingException {
		Contact contact = objectMapper.readValue(jsonString, Contact.class);
		return contact;
	}

}
