package com.adfer.security.controller;

import java.time.format.DateTimeParseException;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.adfer.security.model.AddFoodPlaceRequest;
import com.adfer.security.model.FoodPlace;
import com.adfer.security.service.FoodPlaceServ;
import com.adfer.security.utils.RequestBodyUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/food-places")
public class FoodPlaceController {
	private final FoodPlaceServ foodSiteServ;

	public FoodPlaceController(FoodPlaceServ foodSiteServ) {
		this.foodSiteServ = foodSiteServ;
	}
	
	@Operation(summary = "Get all the food places", description = "Returns a list containing all the food places.")
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "200", description = "Food places successfully retrieved.")
	    })
	@GetMapping
	public ResponseEntity<Object> getAllFoodPlace() {
		return ResponseEntity.ok(foodSiteServ.getAllFoodPlaces());
	}
	
	@Operation(summary = "Get a food place by id", description = "Returns a food place identified by the id parameter.")
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "200", description = "Food place successfully retrieved."), 
		     @ApiResponse(responseCode = "404", description = "The food place was not found.")
	    })
	@GetMapping("/{id}")
	public ResponseEntity<Object> getFoodPlace(
			@PathVariable @Parameter(name = "id", description = "The food place's id", example = "1") Integer id) {
		Optional<FoodPlace> foodPlace = foodSiteServ.getFoodPlace(id);
		
		return !foodPlace.isEmpty()
				? ResponseEntity.ok(foodPlace)
				: ResponseEntity.status(404).body("Food place not found");
	}
	
	@Operation(summary = "Add a new food place", description = "Returns the new food place.")
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "201", description = "Food place successfully added."), 
		     @ApiResponse(responseCode = "400", description = "Bad request. Some mandatory field was not provided or wrong formatted.")
	    })
	@PostMapping
	public ResponseEntity<Object> addFoodPlace (@RequestBody AddFoodPlaceRequest request ) {
		try {
			return ResponseEntity.status(201).body(foodSiteServ.addFoodPlace(request));
		} catch (DateTimeParseException e) {
			return ResponseEntity.status(400).body("{\"error\": \"hour format must be HH:mm\"}");
		}
	}
	

	@Operation(summary = "Update a food place with just the specified fields.", description = "Returns the updated food place.")
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "201", description = "Food place successfully updated."), 
		     @ApiResponse(responseCode = "404", description = "The food place was not found."),
		     @ApiResponse(responseCode = "400", description = "Bad request. Some field was wrong formatted.")
	    })
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateFoodPlace (@PathVariable Integer id, @RequestBody AddFoodPlaceRequest request) throws IllegalArgumentException, IllegalAccessException {
		
		
		if (RequestBodyUtils.isBodyEmpty(request)) {
			return ResponseEntity.status(400).body("You must provide one property at least.");
		}
		
		else if(!foodSiteServ.foodPlaceExists(id)) {
			return ResponseEntity.status(404).body("Food place not found");
		}
		
		try {
			return ResponseEntity.status(201).body(foodSiteServ.updateFoodPlace(request, id));
		} catch (DateTimeParseException e) {
			return ResponseEntity.status(400).body("{\"error\": \"hour format must be HH:mm\"}");
		}
		
	}
	
	@Operation(summary = "Delete a food place.", description = "Deletes the food place identified by the id parameter.")
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "201", description = "Food place successfully deleted."), 
		     @ApiResponse(responseCode = "404", description = "The food place was not found.")
	    })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteFoodPlace (@PathVariable Integer id) {
		
		if (!foodSiteServ.foodPlaceExists(id)) {
			return ResponseEntity.status(404).body("Food place not found.");
		}
		
		foodSiteServ.deleteFoodPlace(id);
		return ResponseEntity.status(201).body("Food place successfully deleted.");
	}
		
}
