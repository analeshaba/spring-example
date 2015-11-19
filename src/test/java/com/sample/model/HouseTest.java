package com.sample.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.sample.model.House;
import com.sample.model.Room;
import com.sample.model.RoomType;

public class HouseTest {

	  @Test
	  public void createEmptyHouse() {

		 House hse = new House(null);
		 assertNotNull(hse);
		 assertEquals(0, hse.getTotalSquareFeet(),0);
		 assertEquals(0, hse.getNumOfRooms());
		 assertNotNull(hse.getSquareFeetByFloor());
		 assertThat(hse.getSquareFeetByFloor().isEmpty(),Matchers.is(true) );
	  }
	  
	  @Test
	  public void createHouseWithEmptyRooms() {
		 List<Room> rooms = new ArrayList<Room>();
		 rooms.add(new Room( 0,0,1,RoomType.BATHROOM));
		 rooms.add(new Room( 0,0,2, RoomType.HALLWAY));
		 House hse = new House(rooms);
		 assertNotNull(hse);
		 assertEquals(0, hse.getTotalSquareFeet(),0);
		 assertEquals(2, hse.getNumOfRooms());
		 assertThat(hse.getSquareFeetByFloor().isEmpty(),Matchers.is(false) );
		 assertEquals(0, hse.getSquareFeetByFloor().get(1),0);
	  }
	  
	  @Test
	  public void createHouseWithRooms() {
		 List<Room> rooms = new ArrayList<Room>();
		 rooms.add(new Room( 11,12.45,2,RoomType.BATHROOM));
		 rooms.add(new Room( 7,7,1,RoomType.BATHROOM));
		 rooms.add(new Room( 5.5,5,3,RoomType.BATHROOM));
		 House hse = new House(rooms);
		 assertNotNull(hse);
		 assertEquals(213.45, hse.getTotalSquareFeet(),0);
		 assertEquals(3, hse.getNumOfRooms());
		 assertThat(hse.getSquareFeetByFloor().isEmpty(),Matchers.is(false) );
		 assertEquals(49.0, hse.getSquareFeetByFloor().get(1),0);
		 assertEquals(136.95, hse.getSquareFeetByFloor().get(2),0);
		 assertEquals(27.5, hse.getSquareFeetByFloor().get(3),0);
	  }
}
