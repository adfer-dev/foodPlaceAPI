package com.adfer.security.model;

import java.util.ArrayList;
import java.util.Set;

public record AddFoodPlaceRequest (
		String name, 
		String address, 
		String telephoneNumber, 
		String websiteUrl,
		String openingTime,
		String closingTime,
		Set<Integer> openingWeekDays,
		ArrayList<Integer> kinds, 
		ArrayList<Integer> services, 
		ArrayList<Integer> placeKinds
) {
	
}