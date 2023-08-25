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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/food-place_kinds")
public class FoodPlaceKindController {

	private final FoodPlaceKindService foodPlaceKindService;

	
	public FoodPlaceKindController(FoodPlaceKindService foodPlaceKindService) {
		this.foodPlaceKindService = foodPlaceKindService;
	}

	@Operation(summary = "Get all the food place kinds", description = "Returns a list containing all the food place kinds.")
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "200", description = "Food place kinds successfully retrieved.")
	    })
	@GetMapping
	public ResponseEntity<Object> getAllFoodPlaceKinds() {
		return ResponseEntity.ok(foodPlaceKindService.getFoodPlaceKinds());
	}
	
	@Operation(summary = "Get a food place kind by id", description = "Returns a food place kind identified by the id parameter.")
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "200", description = "Food place kind successfully retrieved."), 
		     @ApiResponse(responseCode = "404", description = "The food place kind was not found.")
	    })
	@GetMapping("/{id}")
	public ResponseEntity<Object> getFoodPlaceKind(@PathVariable Integer id) {
		Optional<FoodPlaceKind> foodPlaceKind = foodPlaceKindService.getFoodPlaceKind(id);
		
		return !foodPlaceKind.isEmpty()
				? ResponseEntity.ok(foodPlaceKind.get())
				: ResponseEntity.status(404).body("Food place kind not found");
	}
	
	@Operation(summary = "Add a new food place kind", description = "Returns the new food place kind.")
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "201", description = "Food place kind successfully added."), 
		     @ApiResponse(responseCode = "400", description = "Bad request. Some mandatory field was not provided or wrong formatted.")
	    })
	@PostMapping
	public ResponseEntity<Object> addFoodPlaceKind(@RequestBody FoodPlaceKind foodPlaceKind) {
		foodPlaceKindService.addFoodPlaceKind(foodPlaceKind);
		
		return ResponseEntity.status(201).body(foodPlaceKind);
	}
	
}
