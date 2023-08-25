package com.adfer.security.model;

import java.time.LocalTime;
import java.util.Set;
import jakarta.validation.constraints.Pattern;

public class FoodPlaceContact {
	
	private String address;
	@Pattern(
			regexp = "\\d{9}",
			message = "Must be a 9 digit number."
	)
	private String telephoneNumber;
	private String websiteUrl;
	private LocalTime openingTime;
	private LocalTime closingTime;
	private Set<Integer> openingWeekDays;
	
	public FoodPlaceContact() {
	}

	public FoodPlaceContact(String address,
			String telephoneNumber,
			String websiteUrl,
			LocalTime openingTime,
			LocalTime closingTime,
			Set<Integer> openingWeekDays) {
		this.address = address;
		this.telephoneNumber = telephoneNumber;
		this.websiteUrl = websiteUrl;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.openingWeekDays = openingWeekDays;
	}



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public LocalTime getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(LocalTime openingTime) {
		this.openingTime = openingTime;
	}

	public LocalTime getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(LocalTime closingTime) {
		this.closingTime = closingTime;
	}

	public Set<Integer> getOpeningWeekDays() {
		return openingWeekDays;
	}

	public void setOpeningWeekDays(Set<Integer> openingWeekDays) {
		this.openingWeekDays = openingWeekDays;
	}
	
	
}
