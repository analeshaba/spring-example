package com.uptake.repository;

import org.springframework.data.repository.CrudRepository;

import com.uptake.model.House;

public interface HouseRespository extends CrudRepository<House, Long> {

}
