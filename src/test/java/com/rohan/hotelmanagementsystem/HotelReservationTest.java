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
		System.out.println("Adding new hotel to system : ");
		hotelReservation.printAllHotels();
	}

	@Test
	void whenCheapestMethodCalled_shouldReturn_nameOfHotel() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110);
		hotelReservation.addHotel("Bridgewood", 160);
		hotelReservation.addHotel("Ridgewood", 220);
		assertTrue(hotelReservation.cheapestHotel("10 Sep 2020", "11 Sep 2020"));
	}

	@Test
	void whenNewHotelAddedWithWeekdayAndWeekend_shouldReturn_true() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90);
		hotelReservation.addHotel("Bridgewood", 150, 50);
		hotelReservation.addHotel("Ridgewood", 220, 150);
		hotelReservation.printAllHotels();
	}

	@Test
	void whenCheapestMethodWithWeekdayAndWeekendCalled_shouldReturn_nameOfHotels() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90);
		hotelReservation.addHotel("Bridgewood", 150, 50);
		hotelReservation.addHotel("Ridgewood", 220, 150);
		assertTrue(hotelReservation.cheapestHotel("11 Sep 2020", "12 Sep 2020"));
	}

}
