package com.adfer.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.adfer.security.model.FoodKind;
import com.adfer.security.model.FoodPlaceKind;
import com.adfer.security.repository.FoodKindRepository;

/**
 * Class that performs food kinds operations
 */
@Service
public class FoodKindService {
	private final FoodKindRepository foodKindRepository;

	public FoodKindService(FoodKindRepository foodKindRepository) {
		this.foodKindRepository = foodKindRepository;
	}
	
	
	public List<FoodKind> getAllFoodKinds () {
		return foodKindRepository.findAll();
	}
	
	public Optional<FoodKind> getFoodKind (Integer id) {
		return foodKindRepository.findById(id);
	}
	
	public void addFoodKind (FoodKind foodKind) {
		foodKindRepository.save(foodKind);
	}
	
	
	public FoodKind updateFoodKind (Integer id, FoodKind foodKind) {
		
		FoodKind updatedFoodKind = foodKindRepository.findById(id).get();
		
		Optional.ofNullable(foodKind.getName())
			.ifPresent(updatedFoodKind::setName);
		
		Optional.ofNullable(foodKind.getDescription())
			.ifPresent(updatedFoodKind::setDescription);
		
		return updatedFoodKind;
		
	}
	
	public void deleteFoodKind (Integer id) {
		foodKindRepository.deleteById(id);
	}
	
	public boolean foodKindExists (Integer id) {
		return foodKindRepository.existsById(id);
	}
	
}
