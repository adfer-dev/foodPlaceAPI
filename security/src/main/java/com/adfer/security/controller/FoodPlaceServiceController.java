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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/food-place_services")
public class FoodPlaceServiceController {
	
	private final FoodPlaceServiceServ foodPlaceServiceServ;

	public FoodPlaceServiceController(FoodPlaceServiceServ foodPlaceServiceServ) {
		this.foodPlaceServiceServ = foodPlaceServiceServ;
	}

	@Operation(summary = "Get all the food place services", description = "Returns a list containing all the food place services.")
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "200", description = "Food place services successfully retrieved.")
	    })
	@GetMapping
	public ResponseEntity<Object> getAllFoodSiteServices () {
		return ResponseEntity.ok(foodPlaceServiceServ.getAllFoodPlaceServices());
	}
	
	@Operation(summary = "Get a food place service by id", description = "Returns a food place service identified by the id parameter.")
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "200", description = "Food place service successfully retrieved."), 
		     @ApiResponse(responseCode = "404", description = "The food place service was not found.")
	    })
	@GetMapping("/{id}")
	public ResponseEntity<Object> getFoodSite (@PathVariable Integer id) {
		Optional<FoodPlaceService> foodPlaceService = foodPlaceServiceServ.getFoodPlaceService(id);
		
		return !foodPlaceService.isEmpty()
				? ResponseEntity.ok(foodPlaceService.get())
				: ResponseEntity.status(404).body("Food place service not found");
	}
	
	@Operation(summary = "Add a new food place service", description = "Returns the new food place service.")
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "201", description = "Food place service successfully added."), 
		     @ApiResponse(responseCode = "400", description = "Bad request. Some mandatory field was not provided or wrong formatted.")
	    })
	@PostMapping
	public ResponseEntity<Object> addFoodSite (@RequestBody FoodPlaceService foodPlaceService) {
		foodPlaceServiceServ.addFoodPlaceService(foodPlaceService);
		return ResponseEntity.status(201).body(foodPlaceService);
	}

}
