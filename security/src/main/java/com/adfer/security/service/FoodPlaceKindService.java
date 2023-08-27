package com.adfer.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adfer.security.model.FoodPlaceKind;
import com.adfer.security.repository.FoodPlaceKindRepository;

@Service
public class FoodPlaceKindService {

	private final FoodPlaceKindRepository foodPlaceKindRepository;

	public FoodPlaceKindService(FoodPlaceKindRepository fodFoodPlaceKindRepository) {
		this.foodPlaceKindRepository = fodFoodPlaceKindRepository;
	}
	
	public List<FoodPlaceKind> getFoodPlaceKinds () {
		return foodPlaceKindRepository.findAll();
	}
	
	public Optional<FoodPlaceKind> getFoodPlaceKind (Integer id) {
		return foodPlaceKindRepository.findById(id);
	}
	
	public void addFoodPlaceKind (FoodPlaceKind foodPlaceKind) {
		foodPlaceKindRepository.save(foodPlaceKind);
	}
	
	public FoodPlaceKind updateFoodPlaceKind (Integer id, FoodPlaceKind foodPlaceKind) {
		
		FoodPlaceKind updatedFoodPlaceKind = foodPlaceKindRepository.findById(id).get();
		
		Optional.ofNullable(foodPlaceKind.getName())
			.ifPresent(updatedFoodPlaceKind::setName);
		
		Optional.ofNullable(foodPlaceKind.getDescription())
			.ifPresent(updatedFoodPlaceKind::setDescription);
		
		return updatedFoodPlaceKind;
		
	}
	
	public void deleteFoodPlaceKind (Integer id) {
		foodPlaceKindRepository.deleteById(id);
	}
	
	public boolean foodPlaceKindExists (Integer id) {
		return foodPlaceKindRepository.existsById(id);
	}
	
}
