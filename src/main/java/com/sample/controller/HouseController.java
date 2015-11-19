package com.sample.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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
import com.sample.model.House;
import com.sample.model.Room;
import com.sample.repository.HouseRespository;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
public class HouseController {
	private static final Logger log = LoggerFactory
			.getLogger(HouseController.class);

	@Autowired
	HouseRespository repository;

	@RequestMapping(path = "/house", method = RequestMethod.GET)
	public ResponseEntity<List<House>> getAllHouses() {
		List<House> houses = (List<House>) repository.findAll();
		if (Iterables.size(houses) == 0) {
			return new ResponseEntity<List<House>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<House>>(houses, HttpStatus.OK);
	}

	@RequestMapping(path = "/house/{id}", method = RequestMethod.GET)
	public ResponseEntity<House> getHouse(@PathVariable("id") String id) {

		if (StringUtils.isNumber(id)) {
			House hse = repository.findOne(Long.valueOf(id));
			if (hse != null) {
				return new ResponseEntity<House>(hse, HttpStatus.OK);
			}
		}
		return new ResponseEntity<House>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(path = "/house/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<House> deleteHouse(@PathVariable("id") String id) {
		if (StringUtils.isNumber(id)) {
			House hse = repository.findOne(Long.valueOf(id));
			if (hse != null) {
				repository.delete(hse.getId());
				return new ResponseEntity<House>(HttpStatus.NO_CONTENT);
			}
		}
		return new ResponseEntity<House>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/house", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createHouse(
			@RequestBody(required = true) String jsonString) {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			House hse = convertJsonToRooms(jsonString, objectMapper);
			hse = repository.save(hse);
			return new ResponseEntity<House>(hse, HttpStatus.CREATED);
		} catch (JsonParseException e) {
			log.error("JsonParseException: ", e);
		} catch (JsonMappingException e) {
			
			log.error("JsonMappingException: ", e);
		} catch (IOException e) {
			log.error("IOException: ", e);
		}
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);

	}

	@RequestMapping(value = "/house", 
			       method = RequestMethod.PUT, 
			       consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateHouse(
			@RequestBody(required = true) String jsonString) {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			House hse = convertJsonToRooms(jsonString, objectMapper);
			House hseOriginal = repository.findOne(hse.getId());
			if (hseOriginal != null) {
				hse = repository.save(hse);
				return new ResponseEntity<House>(hse, HttpStatus.OK);
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

	private House convertJsonToRooms(String jsonString,
			ObjectMapper objectMapper) throws IOException, JsonParseException,
			JsonMappingException {
		House hse;
		List<Room> rooms = objectMapper.readValue(
				jsonString,
				objectMapper.getTypeFactory().constructCollectionType(
						List.class, Room.class));
		if (CollectionUtils.isNotEmpty(rooms)) {
			//TODO:Fix Update
			hse = new House(rooms);
			if(hse.getNumOfRooms() > 0){
				hse.setId(rooms.get(0).getHouse().getId());
			}
		}else{
			 hse = new House();
		}
		 
		return hse;
	}

}
