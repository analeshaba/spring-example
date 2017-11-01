package com.sample.service;

import java.util.Arrays;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sample.model.Contact;
import com.sample.repository.ContactRespository;

 
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.sample.model"})
@EnableJpaRepositories(basePackages = {"com.sample.repository"})
@ComponentScan(basePackages = {"com.sample.controller","com.sample.config"})
public class ContactApplication {
	private static final Logger log = LoggerFactory.getLogger(ContactApplication.class);
	
	   public static void main(String[] args) {
	        SpringApplication.run(ContactApplication.class, args);
	   }
	   
	   @Bean
		public CommandLineRunner demo(ContactRespository repository) {
			return (args) -> {

				 Contact contact = new Contact("Sample Contact #1");
				 contact.setBirthDate(Calendar.getInstance().getTime());
				 contact.setCompany("ABC Company");
				 contact.setEmail("a@abc.com");
				 contact.setPhoneNumber("(773)224-1830");
				 contact.setAddress("123 N Main Street");
				 contact.setCity("Chicago");
				 contact.setState("il");
				 contact.setCountry("USA");
				 contact.setPostalCode("60600");
				 repository.save(contact);
				 
				 contact = new Contact("Sample Contact #2");
				 contact.setBirthDate(Calendar.getInstance().getTime());
				 contact.setCompany("Tx Company");
				 contact.setEmail("a@abc.com");
				 contact.setPhoneNumber("(773)224-1830");
				 contact.setAddress("123 N Noeth Street");
				 contact.setCity("Dallax");
				 contact.setState("TX");
				 contact.setCountry("USA");
				 contact.setPostalCode("56050");
				 repository.save(contact);
				 

				 contact = new Contact("Sample Contact #3");
				 contact.setBirthDate(Calendar.getInstance().getTime());
				 contact.setCompany("Some Company");
				 contact.setEmail("a@dcCompanu.com");
				 contact.setPhoneNumber("(773)224-1830");
				 contact.setAddress("Happy Way Avenue");
				 contact.setCity("Washington");
				 contact.setState("DC");
				 contact.setCountry("USA");
				 contact.setPostalCode("56050");
				 repository.save(contact);
				 
				log.debug("Demo Data Loaded");
				log.debug("-------------------------------");
				for (Contact n : repository.findAll()) {
					log.info(n.toString());
				}
				
				log.info("Application started with command-line arguments: {} . \n To kill this application, press Ctrl + C.", Arrays.toString(args));
				

			};
	   }
}
