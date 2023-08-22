package com.adfer.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adfer.security.model.FoodPlaceKind;
import com.adfer.security.repository.FoodPlaceKindRepository;

@Service
public class FoodPlaceKindService {

	private final FoodPlaceKindRepository fodFoodPlaceKindRepository;

	public FoodPlaceKindService(FoodPlaceKindRepository fodFoodPlaceKindRepository) {
		this.fodFoodPlaceKindRepository = fodFoodPlaceKindRepository;
	}
	
	public List<FoodPlaceKind> getFoodPlaceKinds () {
		return fodFoodPlaceKindRepository.findAll();
	}
	
	public Optional<FoodPlaceKind> getFoodPlaceKind (Integer id) {
		return fodFoodPlaceKindRepository.findById(id);
	}
	
	public void addFoodPlaceKind (FoodPlaceKind foodPlaceKind) {
		fodFoodPlaceKindRepository.save(foodPlaceKind);
	}
	
}
