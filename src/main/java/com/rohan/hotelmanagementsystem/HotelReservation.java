package com.rohan.hotelmanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class HotelReservation {
	List<Hotel> hotelList;

	public HotelReservation() {
		hotelList = new ArrayList<>();
	}
	
	/**
	 * Adds new hotel to hotel list
	 */
	public boolean addHotel(String name, int regularWeekday) {
		Hotel hotel = new Hotel(name, regularWeekday);
		hotelList.add(hotel);
		return true;
	}
	
	/**
	 * Prints all hotels and rates present in the list
	 * 
	 */
	public void printAllHotels() {
		for(Hotel hotel : hotelList) {
			System.out.println("Hotel Name : " + hotel.getName());
			System.out.println("Rate for regular customer for weekday : " + hotel.getRegularWeekday() + " / day\n");
		}
	}
}
