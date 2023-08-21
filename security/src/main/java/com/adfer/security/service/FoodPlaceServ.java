package com.adfer.security.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.adfer.security.model.AddFoodPlaceRequest;
import com.adfer.security.model.FoodKind;
import com.adfer.security.model.FoodPlace;
import com.adfer.security.model.FoodPlaceKind;
import com.adfer.security.model.FoodPlaceService;
import com.adfer.security.model.FoodPlaceContact;
import com.adfer.security.repository.FoodKindRepository;
import com.adfer.security.repository.FoodPlaceKindRepository;
import com.adfer.security.repository.FoodPlaceRepository;
import com.adfer.security.repository.FoodPlaceServiceRepository;

@Service
public class FoodPlaceServ {
	private final FoodPlaceRepository foodPlaceRepository;
	private final FoodKindRepository foodKindRepository;
	private final FoodPlaceServiceRepository foodPlaceServiceRepository;
	private final FoodPlaceKindRepository foodPlaceKindRepository;
	
	public FoodPlaceServ(FoodPlaceRepository foodSiteRepository, FoodKindRepository foodKindRepository,
			FoodPlaceServiceRepository foodSiteServiceRepository, FoodPlaceKindRepository foodPlaceKindRepository) {
		this.foodPlaceRepository = foodSiteRepository;
		this.foodKindRepository = foodKindRepository;
		this.foodPlaceServiceRepository = foodSiteServiceRepository;
		this.foodPlaceKindRepository = foodPlaceKindRepository;
	}
	
	public List<FoodPlace> getAllFoodPlaces () {
		return foodPlaceRepository.findAll();
	}
	
	public Optional<FoodPlace> getFoodPlace (Integer id){
		return foodPlaceRepository.findById(id);
	}
	
	public FoodPlace addFoodPlace (AddFoodPlaceRequest request) {
		 Set <FoodKind> foodKinds = new HashSet<FoodKind>();
		 Set <FoodPlaceService> foodSiteServices = new HashSet<FoodPlaceService>();
		 Set<FoodPlaceKind> foodPlaceKinds = new HashSet<FoodPlaceKind>();
		 
		 request.foodKinds().forEach(foodKindId -> {
			 foodKinds.add(foodKindRepository.findFoodKindById(foodKindId).get());
		 });
		 
		request.foodServices().forEach(foodServicesId -> {
			foodSiteServices.add(foodPlaceServiceRepository.findFoodPlaceServiceById(foodServicesId).get());
		});
		
		request.placeKinds().forEach(placeKindId -> {
			foodPlaceKinds.add(foodPlaceKindRepository.findFoodPlaceKindById(placeKindId).get());
		});
		
		FoodPlace foodPlace = new FoodPlace(request.name(), new FoodPlaceContact(request.address(), request.telephoneNumber(), request.websiteUrl(), request.schedule())
				, foodKinds, foodSiteServices, foodPlaceKinds);
		
		foodPlaceRepository.save(foodPlace);
		
		return foodPlace;
	}
	
	
}
