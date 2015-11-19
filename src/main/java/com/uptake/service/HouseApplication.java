package com.uptake.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uptake.model.House;
import com.uptake.model.Room;
import com.uptake.model.RoomType;
import com.uptake.repository.HouseRespository;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.uptake.model"})
@EnableJpaRepositories(basePackages = {"com.uptake.repository"})
@ComponentScan(basePackages = {"com.uptake.controller","com.uptake.config"})
public class HouseApplication {
	private static final Logger log = LoggerFactory.getLogger(HouseApplication.class);
	   public static void main(String[] args) {
	        SpringApplication.run(HouseApplication.class, args);
	   }
	   
	   @Bean
		public CommandLineRunner demo(HouseRespository repository) {
			return (args) -> {
			     List<Room> rooms = new ArrayList<Room>();
				 rooms.add(new Room(1,1,1,RoomType.BATHROOM));
				 rooms.add(new Room(2,2,2,RoomType.KITCHEN));

//			   	 rooms.add(new Room(6,12,1));
//			   	 rooms.add(new Room(10,12.45,2));
//			   	 rooms.add(new Room(11,12.45,1));
				 House hse = new House(rooms);
//				 ObjectMapper mapper = new ObjectMapper();
//				 mapper.writeValue(System.out, rooms);
				 repository.save(hse );
				 
			     rooms = new ArrayList<Room>();
			   	 rooms.add(new Room(10,12.45,2,RoomType.BATHROOM));
			   	 rooms.add(new Room(11,12.45,1, RoomType.BEDROOM));
				  hse = new House(rooms);
			
				 repository.save(hse);
				
				log.info("House found with findAll():");
				log.info("-------------------------------");
				for (House n : repository.findAll()) {
					log.info(n.toString());
				}
				
				log.info("House found with by Id:");
				log.info("-------------------------------");
				log.info(repository.findOne(2L).toString());
			};
	   }
}
