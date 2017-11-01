package com.sample.service;

import java.time.LocalDate;
import java.time.Month;

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

				 Contact contact = new Contact("Contact #1");
				 //contact.setBirthDate(LocalDate.of(2014, Month.DECEMBER, 12));
				 contact.setCompany("ABC Company");
				 contact.setEmail("a@a.com");
				 contact.setPhoneNumber("(773)224-1830");
				 repository.save(contact);
				 
				 contact = new Contact("Contact #4");
				 //contact.setBirthDate(LocalDate.of(2019, Month.DECEMBER, 22));
				 contact.setCompany("ABC222 Company");
				 contact.setEmail("a222@a.com");
				 contact.setPhoneNumber("(837)290-2919");
				 repository.save(contact);
				 
//			     rooms = new ArrayList<Address>();
//			   	 rooms.add(new Address(10,12.45,2,RoomType.BATHROOM));
//			   	 rooms.add(new Address(11,12.45,1, RoomType.BEDROOM));
//				 hse = new Contact(rooms);
//				 repository.save(hse );
//			
//			     rooms = new ArrayList<Address>();
//			   	 rooms.add(new Address(10,12.45,2,RoomType.BATHROOM));
//			   	 rooms.add(new Address(11,12.45,1, RoomType.BEDROOM));
//				 hse = new Contact(rooms);
//				 repository.save(hse );
//				 
//			     rooms = new ArrayList<Address>();
//			   	 rooms.add(new Address(10,12.45,2,RoomType.HALLWAY));
//			   	 rooms.add(new Address(11,12.45,1, RoomType.LAUNDRY));
//				 rooms.add(new Address(10,12.45,2,RoomType.BATHROOM));
//			   	 rooms.add(new Address(11,12.45,1, RoomType.BEDROOM));
//				 hse = new Contact(rooms);
//				 repository.save(hse);
				
				log.debug("Demo Data Loaded");
				log.debug("-------------------------------");
				for (Contact n : repository.findAll()) {
					log.info(n.toString());
				}
				

			};
	   }
}
