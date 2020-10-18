package com.rohan.hotelmanagementsystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HotelReservation {
	private static Map<String, Hotel> hotelMap;
	public static String customerType, fromDate, toDate;
	private static int numOfweekdays, numOfWeekends;
	private static TreeMap<Integer, ArrayList<Hotel>> rentMap = new TreeMap<>();

	public HotelReservation() {
		hotelMap = new HashMap<>();
	}

	/**
	 * Adds new hotel to hotel list
	 */
	public void addHotel(String name, int regularWeekday, int regularWeekend, int rating, int rewardWeekday,
			int rewardWeekend) {
		Hotel hotel = new Hotel(name, regularWeekday, regularWeekend, rating, rewardWeekday, rewardWeekend);
		hotelMap.put(name, hotel);
	}

	/**
	 * returns total number of hotels
	 */
	public int totalHotels() {
		return hotelMap.size();
	}

	/**
	 * returns cheapest hotel for given date range
	 */
	public ArrayList<Hotel> cheapestHotels() {
		ArrayList<Hotel> cheapestHotelsList = rentMap.get(rentMap.firstKey());
		return cheapestHotelsList;
	}

	public ArrayList<Hotel> getCheapestHotels() {
		ArrayList<Hotel> cheapestHotelsList = cheapestHotels();
		System.out.println("CHEAPEST HOTELS : ");
		cheapestHotelsList.stream()
				.forEach(hotel -> System.out.print(hotel.getName() + " Rating : " + hotel.getRating() + ", "));
		System.out.print("Rent : " + rentMap.firstKey() + "\n");
		return cheapestHotelsList;
	}

	/**
	 * returns cheapest hotel for given date range having best rating
	 */
	public ArrayList<Hotel> cheapestBestRatedHotel() {
		ArrayList<Hotel> cheapestHotelsList = cheapestHotels();
		int bestRatingInCheapestHotels = cheapestHotelsList.stream().map(hotel -> hotel.getRating()) // Finding maximum
																										// rating in the
																										// cheapest
																										// hotels list
				.max(Integer::compare).get();
		ArrayList<Hotel> cheapestBestRatedHotels = (ArrayList<Hotel>) cheapestHotelsList.stream()
				.filter(hotel -> hotel.getRating() == bestRatingInCheapestHotels).collect(Collectors.toList());
		System.out.print("CHEAPEST BEST RATED HOTELS : ");
		cheapestBestRatedHotels.stream().forEach(hotel -> System.out.print(hotel.getName() + " Rating : "
				+ bestRatingInCheapestHotels + " Rent : " + calculateRent(hotel) + "\n"));
		return cheapestBestRatedHotels;
	}

	/**
	 * creates a map having rent as key and list of hotels as value
	 */
	public static void createRentMap() {
		hotelMap.values()
				.forEach(hotel -> rentMap.computeIfAbsent(calculateRent(hotel), k -> new ArrayList<>()).add(hotel));
	}

	/**
	 * when hotel object passed, return the total rent
	 */
	public static int calculateRent(Hotel h) {
		return (customerType.equalsIgnoreCase("regular"))
				? calculateTotalRent(h.getRegularWeekday(), h.getRegularWeekend())
				: calculateTotalRent(h.getRewardWeekday(), h.getRewardWeekend());
	}

	/**
	 * Calculates the total rent for given date range and rates
	 */
	public static int calculateTotalRent(int weekdayRate, int weekendRate) {
		return weekdayRate * numOfweekdays + weekendRate * numOfWeekends;
	}

	/**
	 * returns number of days in the given range
	 * 
	 * @throws InvalidEntryException
	 */
	public static void numberOfDays() throws InvalidEntryException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
		LocalDate from, to;
		try {
			from = LocalDate.parse(fromDate, formatter); // converting String to LocalDate
			to = LocalDate.parse(toDate, formatter); // converting String to LocalDate
		} catch (DateTimeParseException exception) {
			throw new InvalidEntryException("Inputs are invalid");
		}
		for (LocalDate date = from; date.isBefore(to.plusDays(1)); date = date.plusDays(1)) {
			DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK)); // returns enum of day
			if (day.toString().equalsIgnoreCase("SATURDAY") || day.toString().equalsIgnoreCase("SUNDAY"))
				numOfWeekends++;
			else
				numOfweekdays++;
		}
	}

	/**
	 * this method gives best rated hotel and its rent
	 */
	public ArrayList<Hotel> bestRatedHotel() {
		int maxRating = hotelMap.values().stream().map(hotel -> hotel.getRating()).max(Integer::compare).get();
		ArrayList<Hotel> bestRatedHotelsList = (ArrayList<Hotel>) hotelMap.values().stream()
				.filter(hotel -> hotel.getRating() == maxRating).collect(Collectors.toList());
		System.out.print("BEST RATED HOTELS : ");
		bestRatedHotelsList.stream().forEach(hotel -> System.out
				.print(hotel.getName() + " Rating : " + maxRating + " Rent : " + calculateRent(hotel) + "\n"));
		return bestRatedHotelsList;
	}

	/**
	 * checks if inputs are valid or not
	 */
	public void validateInputs() throws InvalidEntryException {
		String regex = "^[0-9]{2}[A-Za-z]{3}[0-9]{4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcherFrom = pattern.matcher(fromDate);
		Matcher matcherTo = pattern.matcher(toDate);
		if (matcherFrom.find()) {
			if (matcherTo.find()) {
				if (customerType.equalsIgnoreCase("regular") || customerType.equalsIgnoreCase("reward"))
					return;
			}
		}
		throw new InvalidEntryException("Inputs are invalid");
	}

	/**
	 * taking input of customer type and date range from user
	 */
	public void getInput(Scanner scanner) {
		addHotel("Lakewood", 110, 90, 3, 80, 80);
		addHotel("Bridgewood", 150, 50, 4, 110, 50);
		addHotel("Ridgewood", 220, 150, 5, 100, 40);
		System.out.println("<customerType>:<fromDate>,<toDate>");
		String input = scanner.nextLine();
		String[] inputs = input.split("[:,]");
		customerType = inputs[0];
		fromDate = inputs[1];
		toDate = inputs[2];
		try {
			validateInputs();
			numberOfDays();
		} catch (InvalidEntryException exception) {
			System.out.println(exception);
		}
		createRentMap();
	}

	public static void main(String[] args) {
		HotelReservation hotelReservation = new HotelReservation();
		Scanner scanner = new Scanner(System.in);
		hotelReservation.getInput(scanner);
		hotelReservation.getCheapestHotels();
		hotelReservation.cheapestBestRatedHotel();
		hotelReservation.bestRatedHotel();
		scanner.close();
	}
}
