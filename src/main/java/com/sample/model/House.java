package com.sample.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;


@Entity
public class House implements Serializable {

	private static final long serialVersionUID = -3490412181209276754L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "HOUSE_ID")
	private long id;

	@ElementCollection
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "house", cascade = CascadeType.ALL)
	private List<Room> rooms= new ArrayList<Room>();

	@Transient
	private Map<Integer, Double> floorTotalMap;

	public House() {
	}

	public House(final List<Room> rooms) {
		super();
		if (CollectionUtils.isNotEmpty(rooms)) {
			for (Room room : rooms) {
				addRoom(room);
			}	
		}
	}

	public void addRoom(Room room){
		this.rooms.add(room);
	      if (room.getHouse() != this) {
	    	  room.setHouse(this);
	        }
	}
	
	public long getId(){
		return this.id;
	}
	
	
	public void setId(long id) {
		this.id = id;
	}

	public int getNumOfRooms() {
			return rooms.size();
	}

	public double getTotalSquareFeet() {
		double total = 0d;
		floorTotalMap = new HashMap<Integer, Double>();
		if (CollectionUtils.isNotEmpty(rooms)) {
			for (Room room : rooms) {
				total += room.getArea();
				if (floorTotalMap.containsKey(room.getFloor())) {
					floorTotalMap
							.put(room.getFloor(),
									floorTotalMap.get(room.getFloor())
											+ room.getArea());
				} else {
					floorTotalMap.put(room.getFloor(), room.getArea());
				}
			}
		}
		return total;
	}

	public Map<Integer, Double> getSquareFeetByFloor() {
		return floorTotalMap;
	}	
}
