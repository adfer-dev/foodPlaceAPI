package com.adfer.security.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import com.adfer.security.config.RestResponseEntityExceptionHandler;
import com.adfer.security.model.AddFoodPlaceRequest;
import com.adfer.security.model.FoodPlace;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/food-places")
public class FoodPlaceController {
	private final FoodPlaceServ foodSiteServ;
	private final RestResponseEntityExceptionHandler responseEntityExceptionHandler;

	public FoodPlaceController(FoodPlaceServ foodSiteServ, RestResponseEntityExceptionHandler responseEntityExceptionHandler) {
		this.foodSiteServ = foodSiteServ;
		this.responseEntityExceptionHandler = responseEntityExceptionHandler;
	}
	
	@GetMapping
	public ResponseEntity<Object> getAllFoodSites() {
		return ResponseEntity.ok(foodSiteServ.getAllFoodPlaces());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getFoodSite(@PathVariable Integer id) {
		try {
			Optional<FoodPlace> foodPlace = foodSiteServ.getFoodPlace(id);
			
			return !foodPlace.isEmpty()
					? ResponseEntity.ok(foodPlace)
					: ResponseEntity.status(404).body("Food place not found");		
			
		} catch (MethodArgumentTypeMismatchException e) {
			throw new ResponseStatusException(400, "Id must be a number", e);
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> addFoodSite (@Valid @RequestBody AddFoodPlaceRequest request ) {
		return ResponseEntity.ok(foodSiteServ.addFoodPlace(request));
	}
}
