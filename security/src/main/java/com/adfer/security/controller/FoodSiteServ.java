package com.adfer.security.controller;

import java.util.HashSet;
import java.util.Set;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.stereotype.Service;

import com.adfer.security.model.AddFoodSiteRequest;
import com.adfer.security.model.FoodKind;
import com.adfer.security.model.FoodPlace;
import com.adfer.security.model.FoodSiteContact;
import com.adfer.security.model.FoodPlaceService;
import com.adfer.security.repository.FoodKindRepository;
import com.adfer.security.repository.FoodPlaceRepository;
import com.adfer.security.repository.FoodPlaceServiceRepository;

@Service
public class FoodSiteServ {
	private final FoodPlaceRepository foodSiteRepository;
	private final FoodKindRepository foodKindRepository;
	private final FoodPlaceServiceRepository foodSiteServiceRepository;
	
	public FoodSiteServ(FoodPlaceRepository foodSiteRepository, FoodKindRepository foodKindRepository,
			FoodPlaceServiceRepository foodSiteServiceRepository) {
		this.foodSiteRepository = foodSiteRepository;
		this.foodKindRepository = foodKindRepository;
		this.foodSiteServiceRepository = foodSiteServiceRepository;
	}
	
	public void addFoodSite (AddFoodSiteRequest request) {
		 Set <FoodKind> foodKinds = new HashSet<FoodKind>();
		 Set <FoodPlaceService> foodSiteServices = new HashSet<FoodPlaceService>();
		 
		 request.getFoodKinds().forEach(foodKindId -> {
			 foodKinds.add(foodKindRepository.findFoodKindById(foodKindId).get());
		 });
		 
		request.getFoodServices().forEach(foodServicesId -> {
			foodSiteServices.add(foodSiteServiceRepository.findFoodPlaceServiceById(foodServicesId).get());
		});
		
		FoodPlace foodSite = new FoodPlace(new FoodSiteContact(request.getAddress(), request.getTelephoneNumber(), request.getWebsiteUrl(), request.getSchedule())
				, foodKinds, foodSiteServices);
		
		foodSiteRepository.save(foodSite);
		
	}
	
	
}
