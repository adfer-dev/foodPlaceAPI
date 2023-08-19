package com.adfer.security.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
/*
 * Model class that represents a food place's kind (bar, restaurant, gastro, ...)
 */
@Entity
@Table(name = "food-place_kind")
public class FoodPlaceKind {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	@ManyToMany(mappedBy = "kinds")
	@JsonBackReference
	private Set<FoodPlace> foodPlaces;
	
	// Constructors
	
	public FoodPlaceKind() {
	}

	public FoodPlaceKind(String name, String description) {
		this.name = name;
		this.description = description;
	}

	
	// Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<FoodPlace> getFoodPlaces() {
		return foodPlaces;
	}

	public void setFoodPlaces(Set<FoodPlace> foodPlaces) {
		this.foodPlaces = foodPlaces;
	}
	
	
}

