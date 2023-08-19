package com.adfer.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adfer.security.model.FoodKind;
import com.adfer.security.repository.FoodKindRepository;

@RestController
@RequestMapping("/api/v1/food-kinds")
public class FoodKindController {
	
	private final FoodKindRepository foodKindRepository;
	private final FoodKindService foodKindService;
	

	public FoodKindController(FoodKindRepository foodKindRepository, FoodKindService foodKindService) {
		this.foodKindRepository = foodKindRepository;
		this.foodKindService = foodKindService;
	}
	
	@GetMapping
	public ResponseEntity<Object> getAllFoodKinds() {
		return ResponseEntity.ok(foodKindRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getFoodKind(@PathVariable Integer id) {
		return ResponseEntity.ok(foodKindRepository.findFoodKindById(id));
	}
	
	@PostMapping
	public ResponseEntity<Object> addFoodKind(@RequestBody FoodKind foodKind) {
		foodKindService.addFoodKind(foodKind);
		return ResponseEntity.ok(foodKind);
	}
	
}
