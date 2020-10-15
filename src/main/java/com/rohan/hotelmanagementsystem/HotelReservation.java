package com.rohan.hotelmanagementsystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HotelReservation {
	private static Map<String, Hotel> hotelMap;

	public HotelReservation() {
		hotelMap = new HashMap<>();
	}

	/**
	 * Adds new hotel to hotel list with weekdays rent only
	 */
	public boolean addHotel(String name, int regularWeekday) {
		Hotel hotel = new Hotel(name, regularWeekday);
		hotelMap.put(name, hotel);
		return true;
	}

	/**
	 * Adds new hotel to hotel list with weekday and weekend rent
	 */
	public boolean addHotel(String name, int regularWeekday, int regularWeekend) {
		Hotel hotel = new Hotel(name, regularWeekday, regularWeekend);
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
					"Rate for regular customer for weekday : " + entry.getValue().getRegularWeekday() + " / day");
			System.out.println(
					"Rate for regular customer for weekend : " + entry.getValue().getRegularWeekend() + " / day\n");
		}
	}

	/**
	 * returns cheapest hotel for given date range
	 */
	public boolean cheapestHotel(String fromDate, String toDate) {
		Map<Integer, ArrayList<Hotel>> rentMap = createRentMap(fromDate, toDate);
		int minimumRent = Integer.MAX_VALUE;
		for (Map.Entry<Integer, ArrayList<Hotel>> entry : rentMap.entrySet()) {
			if (entry.getKey() < minimumRent)
				minimumRent = entry.getKey();
		}
		System.out.println("Cheapest Hotel : ");
		for (Hotel hotel : rentMap.get(minimumRent)) {
			System.out.print(hotel.getName() + ", ");
		}
		System.out.print("Total Rent : " + minimumRent + "\n");
		return true;
	}

	/**
	 * creates a map having rent as key and list of hotels as value
	 */
	public static Map<Integer, ArrayList<Hotel>> createRentMap(String fromDate, String toDate) {
		HashMap<Integer, ArrayList<Hotel>> rentMap = new HashMap<>();
		int[] numOfDays = numberOfDays(fromDate, toDate);
		for (Map.Entry<String, Hotel> entry : hotelMap.entrySet()) {
			int weekdayRent = entry.getValue().getRegularWeekday() * numOfDays[0];
			int weekendRent = entry.getValue().getRegularWeekend() * numOfDays[1];
			int totalRent = weekdayRent + weekendRent;
			rentMap.computeIfAbsent(totalRent, k -> new ArrayList<>()).add(entry.getValue());
		}
		return rentMap;
	}

	/**
	 * returns number of days in the given range
	 */
	public static int[] numberOfDays(String fromDate, String toDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		// convert String to LocalDate
		LocalDate from = LocalDate.parse(fromDate, formatter);
		LocalDate to = LocalDate.parse(toDate, formatter);
		int numOfweekdays = 0;
		int numOfWeekends = 0;
		for (LocalDate date = from; date.isBefore(to.plusDays(1)); date = date.plusDays(1)) {
			DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK)); // returns enum of day
			switch (day) {
			case SATURDAY:
				numOfWeekends++;
				break;
			case SUNDAY:
				numOfWeekends++;
				break;
			default:
				numOfweekdays++;
				break;
			}
		}
		return new int[] { numOfweekdays, numOfWeekends };
	}

}
