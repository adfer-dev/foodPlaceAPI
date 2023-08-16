package com.adfer.security.controller;

import org.springframework.stereotype.Service;

import com.adfer.security.model.FoodKind;
import com.adfer.security.repository.FoodKindRepository;

@Service
public class FoodKindService {
	private final FoodKindRepository foodKindRepository;

	public FoodKindService(FoodKindRepository foodKindRepository) {
		this.foodKindRepository = foodKindRepository;
	}
	
	public void addFoodKind (FoodKind foodKind) {
		foodKindRepository.save(foodKind);
	}
	
	
}
