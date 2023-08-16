package com.adfer.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adfer.security.model.FoodSiteService;
import com.adfer.security.repository.FoodSiteServiceRepository;

@RestController
@RequestMapping("/api/v1/food-site_services")
public class FoodSiteServiceController {
	
	private final FoodSiteServiceRepository foodSiteServiceRepository;

	public FoodSiteServiceController(FoodSiteServiceRepository foodSiteServiceRepository) {
		this.foodSiteServiceRepository = foodSiteServiceRepository;
	}
	
	@GetMapping
	public ResponseEntity<Object> getAllFoodSiteServices () {
		return ResponseEntity.ok(foodSiteServiceRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getFoodSite (@RequestParam Integer id) {
		return ResponseEntity.ok(foodSiteServiceRepository.findFoodSiteServiceById(id).get());
	}
	
	@PostMapping
	public ResponseEntity<Object> addFoodSite (@RequestBody FoodSiteService foodSiteService) {
		foodSiteServiceRepository.save(foodSiteService);
		return ResponseEntity.ok(foodSiteService);
	}

}
