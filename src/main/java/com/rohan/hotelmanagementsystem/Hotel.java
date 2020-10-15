package com.rohan.hotelmanagementsystem;

public class Hotel {
	private String name;
	private int regularWeekday = 0;
	private int regularWeekend = 0;

	/**
	 * can initiate only name and weekday for regular customers
	 */
	public Hotel(String name, int regularWeekday) {
		this.name = name;
		this.regularWeekday = regularWeekday;
	}

	/**
	 * can initiate name weekday and weekend for regular customers
	 */
	public Hotel(String name, int regularWeekday, int regularWeekend) {
		this.name = name;
		this.regularWeekday = regularWeekday;
		this.regularWeekend = regularWeekend;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRegularWeekday() {
		return regularWeekday;
	}

	public void setRegularWeekday(int regularWeekday) {
		this.regularWeekday = regularWeekday;
	}

	public int getRegularWeekend() {
		return regularWeekend;
	}

	public void setRegularWeekend(int regularWeekend) {
		this.regularWeekend = regularWeekend;
	}

}
