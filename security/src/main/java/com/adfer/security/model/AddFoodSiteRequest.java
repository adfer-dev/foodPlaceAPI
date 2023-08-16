package com.adfer.security.model;

import java.util.ArrayList;

public class AddFoodSiteRequest {
	private String address;
	private String telephoneNumber;
	private String websiteUrl;
	private String schedule;
	private ArrayList<Integer> foodKinds;
	private ArrayList<Integer> foodServices;
	
	public AddFoodSiteRequest(String address, String telephoneNumber, String websiteUrl, String schedule,
			ArrayList<Integer> foodKinds, ArrayList<Integer> foodServices) {
		this.address = address;
		this.telephoneNumber = telephoneNumber;
		this.websiteUrl = websiteUrl;
		this.schedule = schedule;
		this.foodKinds = foodKinds;
		this.foodServices = foodServices;
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
	public ArrayList<Integer> getFoodKinds() {
		return foodKinds;
	}
	public void setFoodKinds(ArrayList<Integer> foodKinds) {
		this.foodKinds = foodKinds;
	}
	public ArrayList<Integer> getFoodServices() {
		return foodServices;
	}
	public void setFoodServices(ArrayList<Integer> foodServices) {
		this.foodServices = foodServices;
	}
	
	
	
}
