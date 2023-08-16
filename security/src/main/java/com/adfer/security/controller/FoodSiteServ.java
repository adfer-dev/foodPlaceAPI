package com.adfer.security.controller;

import java.util.HashSet;
import java.util.Set;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.stereotype.Service;

import com.adfer.security.model.AddFoodSiteRequest;
import com.adfer.security.model.FoodKind;
import com.adfer.security.model.FoodSite;
import com.adfer.security.model.FoodSiteContact;
import com.adfer.security.model.FoodSiteService;
import com.adfer.security.repository.FoodKindRepository;
import com.adfer.security.repository.FoodSiteRepository;
import com.adfer.security.repository.FoodSiteServiceRepository;

@Service
public class FoodSiteServ {
	private final FoodSiteRepository foodSiteRepository;
	private final FoodKindRepository foodKindRepository;
	private final FoodSiteServiceRepository foodSiteServiceRepository;
	
	public FoodSiteServ(FoodSiteRepository foodSiteRepository, FoodKindRepository foodKindRepository,
			FoodSiteServiceRepository foodSiteServiceRepository) {
		this.foodSiteRepository = foodSiteRepository;
		this.foodKindRepository = foodKindRepository;
		this.foodSiteServiceRepository = foodSiteServiceRepository;
	}
	
	public void addFoodSite (AddFoodSiteRequest request) {
		 Set <FoodKind> foodKinds = new HashSet<FoodKind>();
		 Set <FoodSiteService> foodSiteServices = new HashSet<FoodSiteService>();
		 
		 request.getFoodKinds().forEach(foodKindId -> {
			 foodKinds.add(foodKindRepository.findFoodKindById(foodKindId).get());
		 });
		 
		request.getFoodServices().forEach(foodServicesId -> {
			foodSiteServices.add(foodSiteServiceRepository.findFoodSiteServiceById(foodServicesId).get());
		});
		
		FoodSite foodSite = new FoodSite(new FoodSiteContact(request.getAddress(), request.getTelephoneNumber(), request.getWebsiteUrl(), request.getSchedule())
				, foodKinds, foodSiteServices);
		
		foodSiteRepository.save(foodSite);
		
	}
	
	
}
