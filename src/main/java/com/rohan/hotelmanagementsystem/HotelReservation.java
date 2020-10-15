package com.rohan.hotelmanagementsystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HotelReservation {
	private static Map<String, Hotel> hotelMap;

	public HotelReservation() {
		hotelMap = new HashMap<>();
	}

	/**
	 * Adds new hotel to hotel list
	 */
	public boolean addHotel(String name, int regularWeekday) {
		Hotel hotel = new Hotel(name, regularWeekday);
		hotelMap.put(name, hotel);
		return true;
	}

	/**
	 * Prints all hotels and rates present in the list
	 * 
	 */
	public void printAllHotels() {
		for (Map.Entry<String, Hotel> entry : hotelMap.entrySet()) {
			System.out.println("Hotel Name : " + entry.getKey());
			System.out.println(
					"Rate for regular customer for weekday : " + entry.getValue().getRegularWeekday() + " / day\n");
		}
	}

	/**
	 * returns cheapest hotel for given date range
	 */
	public String cheapestHotel(String fromDate, String toDate) {
		Map<Integer, ArrayList<Hotel>> rentMap = createRentMap(fromDate, toDate);
		int minimumRent = Integer.MAX_VALUE;
		for (Map.Entry<Integer, ArrayList<Hotel>> entry : rentMap.entrySet()) {
			if (entry.getKey() < minimumRent)
				minimumRent = entry.getKey();
		}

		System.out.println(
				"Cheapest Hotel : " + rentMap.get(minimumRent).get(0).getName() + "  Total Rent : " + minimumRent);
		return rentMap.get(minimumRent).get(0).getName();
	}

	/**
	 * creates a map having rent as key and list of hotels as value
	 */
	public static Map<Integer, ArrayList<Hotel>> createRentMap(String fromDate, String toDate) {
		HashMap<Integer, ArrayList<Hotel>> rentMap = new HashMap<>();
		int numOfDays = numberOfDays(fromDate, toDate);
		for (Map.Entry<String, Hotel> entry : hotelMap.entrySet()) {
			int rent = entry.getValue().getRegularWeekday() * numOfDays;
			rentMap.computeIfAbsent(rent, k -> new ArrayList<>()).add(entry.getValue());
		}
		return rentMap;
	}

	/**
	 * returns number of days in the given range
	 */
	public static int numberOfDays(String fromDate, String toDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		// convert String to LocalDate
		LocalDate from = LocalDate.parse(fromDate, formatter);
		LocalDate to = LocalDate.parse(toDate, formatter);
		int numOfDays = 0;

		for (LocalDate date = from; date.isBefore(to); date = date.plusDays(1)) {
			numOfDays++;
		}
		return numOfDays;
	}

}
