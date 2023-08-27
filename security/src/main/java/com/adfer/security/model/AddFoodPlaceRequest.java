package com.adfer.security.model;

import java.util.ArrayList;
import java.util.Set;

public class AddFoodPlaceRequest {
    private String name;
    private String address;
    private String telephoneNumber;
    private String websiteUrl;
    private String openingTime;
    private String closingTime;
    private Set<Integer> openingWeekDays;
    private ArrayList<Integer> foodKinds;
    private ArrayList<Integer> services;
    private ArrayList<Integer> kinds;

    public AddFoodPlaceRequest(
            String name,
            String address,
            String telephoneNumber,
            String websiteUrl,
            String openingTime,
            String closingTime,
            Set<Integer> openingWeekDays,
            ArrayList<Integer> foodKinds,
            ArrayList<Integer> services,
            ArrayList<Integer> kinds
    ) {
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.websiteUrl = websiteUrl;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.openingWeekDays = openingWeekDays;
        this.foodKinds = foodKinds;
        this.services = services;
        this.kinds = kinds;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public Set<Integer> getOpeningWeekDays() {
        return openingWeekDays;
    }

    public ArrayList<Integer> getFoodKinds() {
        return foodKinds;
    }

    public ArrayList<Integer> getServices() {
        return services;
    }

    public ArrayList<Integer> getKinds() {
        return kinds;
    }
    
    public boolean isEmpty() {
    	return this.address == null
    			&& this.name == null
    			&& this.telephoneNumber == null 
    			&& this.websiteUrl == null 
    			&& this.openingTime == null 
    			&& this.closingTime == null
    			&& (this.openingWeekDays == null || this.isEmpty())
    			&& (this.foodKinds == null || this.foodKinds.isEmpty())
    			&& (this.services == null || this.services.isEmpty())
    			&& (this.kinds == null || this.kinds.isEmpty());
    }
}