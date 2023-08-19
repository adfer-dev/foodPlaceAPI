package com.adfer.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adfer.security.model.FoodPlaceService;
import com.adfer.security.repository.FoodPlaceServiceRepository;

@RestController
@RequestMapping("/api/v1/food-place_services")
public class FoodPlaceServiceController {
	
	private final FoodPlaceServiceRepository foodSiteServiceRepository;

	public FoodPlaceServiceController(FoodPlaceServiceRepository foodSiteServiceRepository) {
		this.foodSiteServiceRepository = foodSiteServiceRepository;
	}
	
	@GetMapping
	public ResponseEntity<Object> getAllFoodSiteServices () {
		return ResponseEntity.ok(foodSiteServiceRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getFoodSite (@PathVariable Integer id) {
		return ResponseEntity.ok(foodSiteServiceRepository.findFoodPlaceServiceById(id).get());
	}
	
	@PostMapping
	public ResponseEntity<Object> addFoodSite (@RequestBody FoodPlaceService foodSiteService) {
		foodSiteServiceRepository.save(foodSiteService);
		return ResponseEntity.ok(foodSiteService);
	}

}
