package com.adfer.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adfer.security.model.FoodPlaceService;
import com.adfer.security.repository.FoodPlaceServiceRepository;

@Service
public class FoodPlaceServiceServ {
	
	private final FoodPlaceServiceRepository foodPlaceServiceRepository;
	
	public FoodPlaceServiceServ(FoodPlaceServiceRepository foodPlaceServiceRepository) {
		this.foodPlaceServiceRepository = foodPlaceServiceRepository;
	}

	public List<FoodPlaceService> getAllFoodPlaceServices () {
		return foodPlaceServiceRepository.findAll();
	}
	
	public Optional<FoodPlaceService> getFoodPlaceService (Integer id) {
		return foodPlaceServiceRepository.findById(id);
	}
	
	public void addFoodPlaceService (FoodPlaceService foodPlaceService) {
		foodPlaceServiceRepository.save(foodPlaceService);
	}
	
}
