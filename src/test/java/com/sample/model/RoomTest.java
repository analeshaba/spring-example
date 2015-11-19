package com.sample.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.sample.model.Room;
import com.sample.model.RoomType;

public class RoomTest {

	@Test
	  public void createEmptyRoom() {
		
		 Room room = new Room(0,0,0,RoomType.BATHROOM);
		 assertNotNull(room);
		 assertNotNull(room.getType());
		 assertEquals(0, room.getLength(),0);
		 assertEquals(0, room.getWidth(), 0);
		 assertEquals(0, room.getArea(),0);
		 assertEquals(0, room.getFloor());
	  }
	 
	 @Test
	  public void bathroom() {

		 Room room = new Room(11,14.55,1,RoomType.BATHROOM);
		 assertNotNull(room);
 		 assertEquals( room.getType(),RoomType.BATHROOM);
		 assertEquals(11, room.getLength(),0);
		 assertEquals(14.55, room.getWidth(),0);
		 assertEquals(160.05d, room.getArea(),0);
		 assertEquals(1, room.getFloor());
	  }
}
