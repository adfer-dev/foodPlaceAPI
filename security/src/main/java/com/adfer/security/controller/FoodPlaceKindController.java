package com.adfer.security.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.adfer.security.model.FoodPlaceKind;
import com.adfer.security.service.FoodPlaceKindService;

@RestController
@RequestMapping("/api/v1/food-place_kinds")
public class FoodPlaceKindController {

	private final FoodPlaceKindService foodPlaceKindService;

	
	public FoodPlaceKindController(FoodPlaceKindService foodPlaceKindService) {
		this.foodPlaceKindService = foodPlaceKindService;
	}

	@GetMapping
	public ResponseEntity<Object> getAllFoodPlaceKinds() {
		return ResponseEntity.ok(foodPlaceKindService.getFoodPlaceKinds());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getFoodPlaceKind(@PathVariable Integer id) {
		Optional<FoodPlaceKind> foodPlaceKind = foodPlaceKindService.getFoodPlaceKind(id);
		
		return !foodPlaceKind.isEmpty()
				? ResponseEntity.ok(foodPlaceKind.get())
				: ResponseEntity.status(404).body("Food place kind not found");
	}
	
	@PostMapping
	public ResponseEntity<Object> addFoodPlaceKind(@RequestBody FoodPlaceKind foodPlaceKind) {
		foodPlaceKindService.addFoodPlaceKind(foodPlaceKind);
		
		return ResponseEntity.status(201).body(foodPlaceKind);
	}
	
}
