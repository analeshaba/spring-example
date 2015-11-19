package com.sample.repository;

import org.springframework.data.repository.CrudRepository;

import com.sample.model.House;

public interface HouseRespository extends CrudRepository<House, Long> {

}
