package com.sample.repository;


import org.springframework.data.repository.CrudRepository;

import com.sample.model.Room;

public interface RoomRepository extends CrudRepository<Room, Long>{

}
