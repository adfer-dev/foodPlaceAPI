package com.adfer.security.controller;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.adfer.security.model.FoodKind;
import com.adfer.security.service.FoodKindService;

/**
 * Class that handles food kinds endpoints
 */
@RestController
@RequestMapping("/api/v1/food-kinds")
public class FoodKindController {
	
	private final FoodKindService foodKindService;
	

	public FoodKindController(FoodKindService foodKindService) {
		this.foodKindService = foodKindService;
	}
	
	@GetMapping
	public ResponseEntity<Object> getAllFoodKinds() {
		return ResponseEntity.ok(foodKindService.getAllFoodKinds());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getFoodKind(@PathVariable Integer id) {
		Optional<FoodKind> foodKind = foodKindService.getFoodKind(id);
		return !foodKind.isEmpty()
				? ResponseEntity.ok(foodKind.get())
				: ResponseEntity.status(404).body("Food kind not found");
	}
	
	@PostMapping
	public ResponseEntity<Object> addFoodKind(@RequestBody FoodKind foodKind) {
		foodKindService.addFoodKind(foodKind);
		
		return ResponseEntity.status(201).body(foodKind);
	}
	
}
