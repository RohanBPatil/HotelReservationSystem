package com.rohan.hotelmanagementsystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HotelReservationTest {

	@Test
	void whenNewHotelAddedWithWeekday_shouldReturn_true() {
		HotelReservation hotelReservation = new HotelReservation();
		assertTrue(hotelReservation.addHotel("Lakewood", 110));
		assertTrue(hotelReservation.addHotel("Bridgewood", 160));
		assertTrue(hotelReservation.addHotel("Ridgewood", 220));
//		System.out.println("Adding new hotel to system : ");
//		hotelReservation.printAllHotels();
	}

	@Test
	void whenCheapestMethodCalled_shouldReturn_nameOfHotel() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110);
		hotelReservation.addHotel("Bridgewood", 160);
		hotelReservation.addHotel("Ridgewood", 220);
		assertTrue(hotelReservation.cheapestHotel("regular", "10 Sep 2020", "11 Sep 2020"));
	}

	@Test
	void whenNewHotelAddedWithWeekdayAndWeekend_shouldReturn_true() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90);
		hotelReservation.addHotel("Bridgewood", 150, 50);
		hotelReservation.addHotel("Ridgewood", 220, 150);
//		hotelReservation.printAllHotels();
	}

	@Test
	void whenCheapestMethodWithWeekdayAndWeekendCalled_shouldReturn_nameOfHotels() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90);
		hotelReservation.addHotel("Bridgewood", 150, 50);
		hotelReservation.addHotel("Ridgewood", 220, 150);
		assertTrue(hotelReservation.cheapestHotel("regular", "11 Sep 2020", "12 Sep 2020"));
	}

	@Test
	void whenBestRatedMethodCalled_shouldReturn_cheapestAndBestRatedHotel() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90, 3);
		hotelReservation.addHotel("Bridgewood", 150, 50, 4);
		hotelReservation.addHotel("Ridgewood", 220, 150, 5);
		assertTrue(hotelReservation.cheapestBestRatedHotel("regular", "11 Sep 2020", "12 Sep 2020"));
	}

	@Test
	void whenBestRatedMethodCalled_shouldReturn_bestRatedHotel() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90, 3);
		hotelReservation.addHotel("Bridgewood", 150, 50, 4);
		hotelReservation.addHotel("Ridgewood", 220, 150, 5);
		assertTrue(hotelReservation.bestRatedHotelForGivenDates("11 Sep 2020", "12 Sep 2020"));
	}

	@Test
	void whenNewHotelAddedWithWeekdayAndWeekendAndRewardCustomer_shouldReturn_true() {
		HotelReservation hotelReservation = new HotelReservation();
		assertTrue(hotelReservation.addHotel("Lakewood", 110, 90, 3, 80, 80));
		assertTrue(hotelReservation.addHotel("Bridgewood", 150, 50, 4, 110, 50));
		assertTrue(hotelReservation.addHotel("Ridgewood", 220, 150, 5, 100, 40));
	}

	@Test
	void cheapestBestRatedMethodForRewardCustomer_shouldPrintAndReturn_true() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90, 3, 80, 80);
		hotelReservation.addHotel("Bridgewood", 150, 50, 4, 110, 50);
		hotelReservation.addHotel("Ridgewood", 220, 150, 5, 100, 40);
		assertTrue(hotelReservation.cheapestBestRatedHotel("Rewards", "11 Sep 2020", "12 Sep 2020"));
	}

	@Test
	void whenInvalidEntriesAreGiven_shouldThrow_InvalidEntryException() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90, 3, 80, 80);
		hotelReservation.addHotel("Bridgewood", 150, 50, 4, 110, 50);
		hotelReservation.addHotel("Ridgewood", 220, 150, 5, 100, 40);
		assertThrows(InvalidEntryException.class, () -> {
			hotelReservation.validateInputs("NonRewards", "11 Sep 2020", "12 Sep 2020");
		});
	}
}
