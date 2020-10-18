package com.rohan.hotelmanagementsystem;

public class Hotel {
	private String name;
	private int regularWeekday = 0;
	private int regularWeekend = 0;
	private int rating = 0;
	private int rewardWeekday = 0, rewardWeekend = 0;

	/**
	 * initiating all fields
	 */
	public Hotel(String name, int regularWeekday, int regularWeekend, int rating, int rewardWeekday,
			int rewardWeekend) {
		this.name = name;
		this.regularWeekday = regularWeekday;
		this.regularWeekend = regularWeekend;
		this.rating = rating;
		this.rewardWeekday = rewardWeekday;
		this.rewardWeekend = rewardWeekend;
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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getRewardWeekday() {
		return rewardWeekday;
	}

	public void setRewardWeekday(int rewardWeekday) {
		this.rewardWeekday = rewardWeekday;
	}

	public int getRewardWeekend() {
		return rewardWeekend;
	}

	public void setRewardWeekend(int rewardWeekend) {
		this.rewardWeekend = rewardWeekend;
	}

}
