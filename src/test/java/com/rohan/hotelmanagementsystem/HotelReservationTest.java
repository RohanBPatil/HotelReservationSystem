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
		hotelReservation.printAllHotels();
	}

	@Test
	void whenCheapestMethodCalled_shouldReturn_nameOfHotel() {
		HotelReservation hotelReservation = new HotelReservation();
		assertTrue(hotelReservation.addHotel("Lakewood", 110));
		assertTrue(hotelReservation.addHotel("Bridgewood", 160));
		assertTrue(hotelReservation.addHotel("Ridgewood", 220));
		assertEquals("Lakewood", hotelReservation.cheapestHotel("10 Sep 2020", "11 Sep 2020"));
	}

	@Test
	void whenNewHotelAddedWithWeekdayAndWeekend_shouldReturn_true() {
		HotelReservation hotelReservation = new HotelReservation();
		assertTrue(hotelReservation.addHotel("Lakewood", 110, 90));
		assertTrue(hotelReservation.addHotel("Bridgewood", 150, 50));
		assertTrue(hotelReservation.addHotel("Ridgewood", 220, 150));
		hotelReservation.printAllHotels();
	}

}
