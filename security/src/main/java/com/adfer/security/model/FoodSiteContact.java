package com.adfer.security.model;

public class FoodSiteContact {

	private String address;
	private String telephoneNumber;
	private String websiteUrl;
	private String schedule;
	
	public FoodSiteContact() {
	}

	public FoodSiteContact(String address, String telephoneNumber, String websiteUrl, String schedule) {
		this.address = address;
		this.telephoneNumber = telephoneNumber;
		this.websiteUrl = websiteUrl;
		this.schedule = schedule;
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

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
}
