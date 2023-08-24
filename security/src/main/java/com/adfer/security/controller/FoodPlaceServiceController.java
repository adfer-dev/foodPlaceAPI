package com.adfer.security.controller;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.adfer.security.model.FoodPlaceService;
import com.adfer.security.service.FoodPlaceServiceServ;

@RestController
@RequestMapping("/api/v1/food-place_services")
public class FoodPlaceServiceController {
	
	private final FoodPlaceServiceServ foodPlaceServiceServ;

	public FoodPlaceServiceController(FoodPlaceServiceServ foodPlaceServiceServ) {
		this.foodPlaceServiceServ = foodPlaceServiceServ;
	}

	@GetMapping
	public ResponseEntity<Object> getAllFoodSiteServices () {
		return ResponseEntity.ok(foodPlaceServiceServ.getAllFoodPlaceServices());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getFoodSite (@PathVariable Integer id) {
		Optional<FoodPlaceService> foodPlaceService = foodPlaceServiceServ.getFoodPlaceService(id);
		
		return !foodPlaceService.isEmpty()
				? ResponseEntity.ok(foodPlaceService.get())
				: ResponseEntity.status(404).body("Food place service not found");
	}
	
	@PostMapping
	public ResponseEntity<Object> addFoodSite (@RequestBody FoodPlaceService foodPlaceService) {
		foodPlaceServiceServ.addFoodPlaceService(foodPlaceService);
		return ResponseEntity.status(201).body(foodPlaceService);
	}

}
