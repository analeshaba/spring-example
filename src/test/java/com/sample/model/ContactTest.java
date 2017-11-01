package com.sample.model;

import static org.junit.Assert.assertNotNull;


import org.junit.Test;

import com.sample.model.Contact;

public class ContactTest {

	  @Test
	  public void createEmptyContact() {

		 Contact contact = new Contact();
		 assertNotNull(contact);
	  }
	  
//	  @Test
//	  public void createHouseWithEmptyRooms() {
//		 List<Address> rooms = new ArrayList<Address>();
//		 rooms.add(new Address( 0,0,1,RoomType.BATHROOM));
//		 rooms.add(new Address( 0,0,2, RoomType.HALLWAY));
//		 Contact hse = new Contact(rooms);
//		 assertNotNull(hse);
//		 assertEquals(0, hse.getTotalSquareFeet(),0);
//		 assertEquals(2, hse.getNumOfRooms());
//		 assertThat(hse.getSquareFeetByFloor().isEmpty(),Matchers.is(false) );
//		 assertEquals(0, hse.getSquareFeetByFloor().get(1),0);
//	  }
//	  
//	  @Test
//	  public void createHouseWithRooms() {
//		 List<Address> rooms = new ArrayList<Address>();
//		 rooms.add(new Address( 11,12.45,2,RoomType.BATHROOM));
//		 rooms.add(new Address( 7,7,1,RoomType.BATHROOM));
//		 rooms.add(new Address( 5.5,5,3,RoomType.BATHROOM));
//		 Contact hse = new Contact(rooms);
//		 assertNotNull(hse);
//		 assertEquals(213.45, hse.getTotalSquareFeet(),0);
//		 assertEquals(3, hse.getNumOfRooms());
//		 assertThat(hse.getSquareFeetByFloor().isEmpty(),Matchers.is(false) );
//		 assertEquals(49.0, hse.getSquareFeetByFloor().get(1),0);
//		 assertEquals(136.95, hse.getSquareFeetByFloor().get(2),0);
//		 assertEquals(27.5, hse.getSquareFeetByFloor().get(3),0);
//	  }
}
