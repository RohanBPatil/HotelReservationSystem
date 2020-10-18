package com.rohan.hotelmanagementsystem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class HotelReservationTest {
	static HotelReservation hotelReservation;

	/**
	 * Initiating hotelReservation object and adding hotels to it before testing
	 */
	@BeforeAll
	static void initiatingHotelReservationAndAddingHotels() {
		hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90, 3, 80, 80);
		hotelReservation.addHotel("Bridgewood", 150, 50, 4, 110, 50);
		hotelReservation.addHotel("Ridgewood", 220, 150, 5, 100, 40);
	}

	/**
	 * tests add method
	 */
	@Test
	void whenNewHotelAdded_shouldReturn_totalHotelsAdded() {
		HotelReservation.customerType = "regular";
		HotelReservation.fromDate = "11Sep2020";
		HotelReservation.toDate = "12Sep2020";
		try {
			HotelReservation.numberOfDays();
		} catch (InvalidEntryException e) {
			e.printStackTrace();
		}
		HotelReservation.createRentMap();
		int totalHotelsAdded = hotelReservation.totalHotels();
		assertEquals(3, totalHotelsAdded);
	}

	/**
	 * cheapest hotels for regular customer
	 */
	@Test
	void whenCheapestHotelMethodCalled_shouldReturn_listOfHotelForRegularCustomer() {
		HotelReservation.customerType = "regular";
		HotelReservation.fromDate = "11Sep2020";
		HotelReservation.toDate = "12Sep2020";
		try {
			HotelReservation.numberOfDays();
		} catch (InvalidEntryException e) {
			e.printStackTrace();
		}
		HotelReservation.createRentMap();
		ArrayList<Hotel> cheapestHotelsResult = hotelReservation.getCheapestHotels();
		assertEquals("Bridgewood", cheapestHotelsResult.get(0).getName());
		assertEquals("Lakewood", cheapestHotelsResult.get(1).getName());
	}

	/**
	 * cheapest hotels for reward customer
	 */
	@Test
	void whenCheapestHotelMethodCalled_shouldReturn_listOfHotelForRewardCustomer() {
		HotelReservation.customerType = "reward";
		HotelReservation.fromDate = "11Sep2020";
		HotelReservation.toDate = "12Sep2020";
		try {
			HotelReservation.numberOfDays();
		} catch (InvalidEntryException e) {
			e.printStackTrace();
		}
		HotelReservation.createRentMap();
		ArrayList<Hotel> cheapestHotelsResult = hotelReservation.getCheapestHotels();
		assertEquals("Bridgewood", cheapestHotelsResult.get(0).getName());
		assertEquals("Lakewood", cheapestHotelsResult.get(1).getName());
	}

	/**
	 * tests cheapest best rated hotel for regular customer
	 */
	@Test
	void whenBestRatedMethodCalled_shouldReturn_cheapestAndBestRatedHotelforRegularCustomer() {
		HotelReservation.customerType = "regular";
		HotelReservation.fromDate = "11Sep2020";
		HotelReservation.toDate = "12Sep2020";
		try {
			HotelReservation.numberOfDays();
		} catch (InvalidEntryException e) {
			e.printStackTrace();
		}
		HotelReservation.createRentMap();
		ArrayList<Hotel> cheapestBestRatedHotelsResult = hotelReservation.cheapestBestRatedHotel();
		assertEquals("Bridgewood", cheapestBestRatedHotelsResult.get(0).getName());
	}

	/**
	 * tests cheapest best rated hotel for reward customer
	 */
	@Test
	void whenBestRatedMethodCalled_shouldReturn_cheapestAndBestRatedHotelforRewardCustomer() {
		HotelReservation.customerType = "reward";
		HotelReservation.fromDate = "11Sep2020";
		HotelReservation.toDate = "12Sep2020";
		try {
			HotelReservation.numberOfDays();
		} catch (InvalidEntryException e) {
			e.printStackTrace();
		}
		HotelReservation.createRentMap();
		ArrayList<Hotel> cheapestBestRatedHotelsResult = hotelReservation.cheapestBestRatedHotel();
		assertEquals("Bridgewood", cheapestBestRatedHotelsResult.get(0).getName());
	}

	/**
	 * tests best rated hotels from all hotels
	 */
	@Test
	void bestRatedHotelMethod_shouldReturn_bestRatedHotel() {
		ArrayList<Hotel> bestRatedHotelResult = hotelReservation.bestRatedHotel();
		assertEquals("Ridgewood", bestRatedHotelResult.get(0).getName());
	}

	/**
	 * given wrong pattern of fromDate should throw InvalidEntryException
	 */
	@Test
	void givenWrongPatternOfFromDate_shouldThrow_InvalidEntryException() {
		HotelReservation.customerType = "reward";
		HotelReservation.fromDate = "11Sep202"; // wrong pattern
		HotelReservation.toDate = "12Sep2020";
		assertThrows(InvalidEntryException.class, () -> hotelReservation.validateInputs());
	}

	/**
	 * given wrong pattern of toDate should throw InvalidEntryException
	 */
	@Test
	void givenWrongPatternOfToDate_shouldThrow_InvalidEntryException() {
		HotelReservation.customerType = "reward";
		HotelReservation.fromDate = "11Sep2020";
		HotelReservation.toDate = "12-10-2020"; // wrong pattern
		assertThrows(InvalidEntryException.class, () -> hotelReservation.validateInputs());
	}

	/**
	 * given customer type other than regular and reward should throw
	 * InvalidEntryException
	 */
	@Test
	void givenWrongCustomerType_shouldThrow_InvalidEntryException() {
		HotelReservation.customerType = "prime member"; // wrong customer type
		HotelReservation.fromDate = "11Sep2020";
		HotelReservation.toDate = "12Sep2020"; // wrong pattern
		assertThrows(InvalidEntryException.class, () -> hotelReservation.validateInputs());
	}

}
