package com.sample.model;

import static org.junit.Assert.assertNotNull;


import org.junit.Test;

import com.sample.model.Contact;

public class ContactTest {

	  @Test
	  public void createEmptyContact() {

		 Contact contact = new Contact("Test Data");
		 assertNotNull(contact);
	  }
	  
}
