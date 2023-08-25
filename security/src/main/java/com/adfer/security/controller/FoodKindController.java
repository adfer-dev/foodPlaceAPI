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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
	
	@Operation(summary = "Get all the food kinds", description = "Returns a list containing all the food kinds.")
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "200", description = "Food kinds successfully retrieved.")
	    })
	@GetMapping
	public ResponseEntity<Object> getAllFoodKinds() {
		return ResponseEntity.ok(foodKindService.getAllFoodKinds());
	}
	
	@Operation(summary = "Get a food kind by id", description = "Returns a food kind identified by the id parameter.")
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "200", description = "Food kind successfully retrieved."), 
		     @ApiResponse(responseCode = "404", description = "The food kind was not found.")
	    })
	@GetMapping("/{id}")
	public ResponseEntity<Object> getFoodKind(@PathVariable Integer id) {
		Optional<FoodKind> foodKind = foodKindService.getFoodKind(id);
		return !foodKind.isEmpty()
				? ResponseEntity.ok(foodKind.get())
				: ResponseEntity.status(404).body("Food kind not found");
	}
	
	@Operation(summary = "Add a new food kind", description = "Returns the new food kind.")
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "201", description = "Food kind successfully added."), 
		     @ApiResponse(responseCode = "400", description = "Bad request. Some mandatory field was not provided or wrong formatted.")
	    })
	@PostMapping
	public ResponseEntity<Object> addFoodKind(@RequestBody FoodKind foodKind) {
		foodKindService.addFoodKind(foodKind);
		
		return ResponseEntity.status(201).body(foodKind);
	}
	
}
