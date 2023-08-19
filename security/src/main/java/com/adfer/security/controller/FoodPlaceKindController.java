package com.adfer.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adfer.security.model.FoodPlaceKind;
import com.adfer.security.repository.FoodPlaceKindRepository;

@RestController
@RequestMapping("/api/v1/food-place_kinds")
public class FoodPlaceKindController {

	private final FoodPlaceKindRepository foodPlaceKindRepository;

	public FoodPlaceKindController(FoodPlaceKindRepository foodPlaceKindRepository) {
		this.foodPlaceKindRepository = foodPlaceKindRepository;
	}
	
	
	@GetMapping
	public ResponseEntity<Object> getAllFoodPlaceKinds() {
		return ResponseEntity.ok(foodPlaceKindRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getFoodPlaceKind(@PathVariable Integer id) {
		return ResponseEntity.ok(foodPlaceKindRepository.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Object> addFoodPlaceKind(@RequestBody FoodPlaceKind foodPlaceKind) {
		foodPlaceKindRepository.save(foodPlaceKind);
		return ResponseEntity.ok(foodPlaceKind);
	}
	
}
