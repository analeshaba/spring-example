package com.sample.service;

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

import com.sample.model.House;
import com.sample.model.Room;
import com.sample.model.RoomType;
import com.sample.repository.HouseRespository;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.sample.model"})
@EnableJpaRepositories(basePackages = {"com.sample.repository"})
@ComponentScan(basePackages = {"com.sample.controller","com.sample.config"})
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
				 House hse = new House(rooms);
				 repository.save(hse );
				 
			     rooms = new ArrayList<Room>();
			   	 rooms.add(new Room(10,12.45,2,RoomType.BATHROOM));
			   	 rooms.add(new Room(11,12.45,1, RoomType.BEDROOM));
				 hse = new House(rooms);
				 repository.save(hse );
			
			     rooms = new ArrayList<Room>();
			   	 rooms.add(new Room(10,12.45,2,RoomType.BATHROOM));
			   	 rooms.add(new Room(11,12.45,1, RoomType.BEDROOM));
				 hse = new House(rooms);
				 repository.save(hse );
				 
			     rooms = new ArrayList<Room>();
			   	 rooms.add(new Room(10,12.45,2,RoomType.HALLWAY));
			   	 rooms.add(new Room(11,12.45,1, RoomType.LAUNDRY));
				 rooms.add(new Room(10,12.45,2,RoomType.BATHROOM));
			   	 rooms.add(new Room(11,12.45,1, RoomType.BEDROOM));
				 hse = new House(rooms);
				 repository.save(hse);
				
				log.debug("Demo Data Loaded");
				log.debug("-------------------------------");
				for (House n : repository.findAll()) {
					log.info(n.toString());
				}
				

			};
	   }
}
