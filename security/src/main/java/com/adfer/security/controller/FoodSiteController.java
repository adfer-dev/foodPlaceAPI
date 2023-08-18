package com.adfer.security.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adfer.security.model.AddFoodSiteRequest;
import com.adfer.security.model.FoodPlace;
import com.adfer.security.repository.FoodPlaceRepository;


@RestController
@RequestMapping("/api/v1/food-places")
public class FoodSiteController {
	private final FoodPlaceRepository foodSiteRepository;
	private final FoodSiteServ foodSiteServ;

	public FoodSiteController(FoodPlaceRepository foodSiteRepository, FoodSiteServ foodSiteServ) {
		this.foodSiteRepository = foodSiteRepository;
		this.foodSiteServ = foodSiteServ;
	}
	
	@GetMapping
	public ResponseEntity<Object> getAllFoodSites() {
		List<FoodPlace> foodSites = foodSiteRepository.findAll();
		return ResponseEntity.ok(foodSites);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getFoodSite(@RequestParam Integer id) {
		return ResponseEntity.ok(foodSiteRepository.findFoodPlaceById(id).get());
	}
	
	@PostMapping
	public ResponseEntity<Object> addFoodSite (@RequestBody AddFoodSiteRequest request ) {
		foodSiteServ.addFoodSite(request);
		return ResponseEntity.ok("food site added");
	}
}
