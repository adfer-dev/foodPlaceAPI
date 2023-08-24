package com.adfer.security.controller;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.adfer.security.model.AddFoodPlaceRequest;
import com.adfer.security.model.FoodPlace;
import com.adfer.security.service.FoodPlaceServ;



@RestController
@RequestMapping("/api/v1/food-places")
public class FoodPlaceController {
	private final FoodPlaceServ foodSiteServ;

	public FoodPlaceController(FoodPlaceServ foodSiteServ) {
		this.foodSiteServ = foodSiteServ;
	}
	
	@GetMapping
	public ResponseEntity<Object> getAllFoodPlace() {
		return ResponseEntity.ok(foodSiteServ.getAllFoodPlaces());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getFoodPlace(@PathVariable Integer id) {
		Optional<FoodPlace> foodPlace = foodSiteServ.getFoodPlace(id);
		
		return !foodPlace.isEmpty()
				? ResponseEntity.ok(foodPlace)
				: ResponseEntity.status(404).body("Food place not found");
	}
	
	@PostMapping
	public ResponseEntity<FoodPlace> addFoodPlace (@RequestBody AddFoodPlaceRequest request ) {
		return ResponseEntity.status(201).body(foodSiteServ.addFoodPlace(request));
	}
}
