package com.adfer.security.model;

import java.util.ArrayList;

public record AddFoodPlaceRequest (
		String name, 
		String address, 
		String telephoneNumber, 
		String websiteUrl, 
		String schedule,
		ArrayList<Integer> foodKinds, 
		ArrayList<Integer> foodServices, 
		ArrayList<Integer> placeKinds
) {
	
}