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

	public boolean addHotel(String name, int regularWeekday, int regularWeekend) {
		Hotel hotel = new Hotel(name, regularWeekday, regularWeekend);
		hotelMap.put(name, hotel);
		return true;
	}

	public boolean addHotel(String name, int regularWeekday, int regularWeekend, int rating) {
		Hotel hotel = new Hotel(name, regularWeekday, regularWeekend, rating);
		hotelMap.put(name, hotel);
		return true;
	}

	public boolean addHotel(String name, int regularWeekday, int regularWeekend, int rating, int rewardWeekday,
			int rewardWeekend) {
		Hotel hotel = new Hotel(name, regularWeekday, regularWeekend, rating, rewardWeekday, rewardWeekend);
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
			System.out.println("Rating : " + entry.getValue().getRating());
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
		System.out.print("Cheapest Hotel : ");
		for (Hotel hotel : rentMap.get(minimumRent)) {
			System.out.print(hotel.getName() + ", ");
		}
		System.out.print("Total Rent : " + minimumRent + "\n");
		return true;
	}

	/**
	 * returns cheapest hotel for given date range having best rating
	 */
	public boolean cheapestBestRatedHotel(String fromDate, String toDate) {
		Map<Integer, ArrayList<Hotel>> rentMap = createRentMap(fromDate, toDate);
		int minimumRent = Integer.MAX_VALUE;
		for (Map.Entry<Integer, ArrayList<Hotel>> entry : rentMap.entrySet()) {
			if (entry.getKey() < minimumRent)
				minimumRent = entry.getKey();
		}
		ArrayList<Hotel> cheapestHotels = rentMap.get(minimumRent);
		String bestRatedCheapestHotel = "";
		int rating = 0;
		for (Hotel hotel : cheapestHotels) {
			if (hotel.getRating() > rating) {
				bestRatedCheapestHotel = hotel.getName();
				rating = hotel.getRating();
			}
		}
		System.out.println("Cheapest Best Rated Hotel : " + bestRatedCheapestHotel + " Rating : " + rating
				+ " Total Rent : " + minimumRent + "\n");
		return true;
	}

	/**
	 * creates a map having rent as key and list of hotels as value
	 */
	public static Map<Integer, ArrayList<Hotel>> createRentMap(String fromDate, String toDate) {
		HashMap<Integer, ArrayList<Hotel>> rentMap = new HashMap<>();
		for (Map.Entry<String, Hotel> entry : hotelMap.entrySet()) {
			int totalRent = calculateRent(fromDate, toDate, entry.getValue().getRegularWeekday(),
					entry.getValue().getRegularWeekend());
			rentMap.computeIfAbsent(totalRent, k -> new ArrayList<>()).add(entry.getValue());
		}
		return rentMap;
	}

	/**
	 * Calculates the total rent for given date range and rates
	 */
	public static int calculateRent(String fromDate, String toDate, int weekdayRate, int weekendRate) {
		int[] numOfDays = numberOfDays(fromDate, toDate);
		int weekdayRent = weekdayRate * numOfDays[0];
		int weekendRent = weekendRate * numOfDays[1];
		int totalRent = weekdayRent + weekendRent;
		return totalRent;
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

	/**
	 * this method gives best rated hotel and its rent
	 */
	public boolean bestRatedHotelForGivenDates(String fromDate, String toDate) {
		int rating = 0;
		int rent = 0;
		String bestRatedHotel = "";
		for (Map.Entry<String, Hotel> entry : hotelMap.entrySet()) {
			if (entry.getValue().getRating() > rating) {
				rating = entry.getValue().getRating();
				bestRatedHotel = entry.getKey();
				rent = calculateRent(fromDate, toDate, entry.getValue().getRegularWeekday(),
						entry.getValue().getRegularWeekend());
			}
		}
		System.out.println("Best rated hotel : " + bestRatedHotel + ", Rent : " + rent);
		return true;
	}

}
