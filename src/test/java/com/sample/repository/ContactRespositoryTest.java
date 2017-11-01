package com.sample.repository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.model.Contact;
 


@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = com.sample.service.ContactApplication.class)
public class ContactRespositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ContactRespository contactRepository;
    
    @Before
    public void loadTestData(){
    	Contact contact = new Contact("Jane Doe 1");
    	contact.setBirthDate(Calendar.getInstance().getTime());
    	contact.setState("IL");
    	contact.setCity("Chicago");
    	contact.setEmail("contact@chicago.co.uk");
    	contact.setPostalCode("10012");
    	contact.setPhoneNumber("(904)123-4343");
        entityManager.persist(contact);
        
    	Contact contact2 = new Contact("Jane NY");
    	contact2.setBirthDate(Calendar.getInstance().getTime());
    	contact2.setState("NY");
    	contact2.setCity("new york");
    	contact2.setEmail("contact@chicago.com");
    	contact2.setPhoneNumber("(773)838-1937");
    	contact2.setPostalCode("60646");
        entityManager.persist(contact2);	
    }
 
    @Test
    public void testFindByPhone() {
 
    	List<Contact> queryResults = contactRepository.findByPhoneNumber("(773)838-1937");

        assertFalse(queryResults.isEmpty());
        assertThat(queryResults).extracting(Contact::getPhoneNumber).containsOnly("(773)838-1937");
    }
    
    @Test
    public void testFindByEmail() {
 
    	List<Contact> queryResults = contactRepository.findByEmail("contact@chicago.co.uk".toUpperCase());

        assertFalse(queryResults.isEmpty());
        assertThat(queryResults).extracting(Contact::getEmail).containsOnly("contact@chicago.co.uk".toUpperCase());
    }
    @Test
    public void testFindByCity() {
 
    	List<Contact> queryResults = contactRepository.findByCity("New York".toUpperCase());

        assertFalse(queryResults.isEmpty());
        assertThat(queryResults).extracting(Contact::getCity).containsOnly("New York".toUpperCase());
    }
    
    @Test
    public void testFindByState() {
 
    	List<Contact> queryResults = contactRepository.findByState("Ny".toUpperCase());

    	assertThat(queryResults).isNotEmpty();
        assertThat(queryResults).extracting(Contact::getState).containsOnly("NY");
    }
}